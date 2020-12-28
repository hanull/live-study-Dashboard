import org.kohsuke.github.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class LiveStudyDashboard {

    private GitHub gitHub;
    private GHRepository repository;
    private TreeMap<String, boolean[]> participantList;

    public LiveStudyDashboard(String OAuthToken, String path) throws IOException {
        gitHub = new GitHubBuilder().withOAuthToken(OAuthToken).build();
        repository = gitHub.getRepository(path);
        participantList = new TreeMap<>();
    }

    public void checkParticipants() throws IOException {
        for (GHIssue issue : repository.getIssues(GHIssueState.ALL)) {
            for (GHIssueComment comment : issue.listComments()) {
                String participant = comment.getUser().getLogin();
                int issueNumber = issue.getNumber();
                if (!participantList.containsKey(participant)) {
                    participantList.put(participant, new boolean[19]);
                }
                boolean[] checked = participantList.get(participant);
                checked[issueNumber] = true;
            }
        }
    }

    public void createBoard() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("| 참여자 | 1주차 | 2주차 | 3주차 | 4주차 | 5주차 | 6주차 | 7주차 | 8주차 | 9주차 | 10주차 | 11주차 | 12주차 | 13주차 | 14주차 | 15주차 | 16주차 | 17주차 | 18주차 | 참석율 |\n");

        for (String participant : participantList.keySet()) {
            boolean[] checked = participantList.get(participant);
            float cnt = 0;
            bw.write("| " + participant + " |");
            for (int i = 1; i <= 18; i++) {
                if (checked[i]) {
                    bw.write(":white_check_mark:");
                    cnt++;
                }
                bw.write(" |");
            }
            float rate = Float.parseFloat(String.format("%.2f", cnt / 18 * 100));
            bw.write(rate + " |");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

}

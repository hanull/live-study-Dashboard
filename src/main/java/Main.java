import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final String OAuthToken = "9a236c15b7230b5f75a597c117d311f61e8f8a50";
        final String repoPath = "whiteship/live-study";

        LiveStudyDashboard liveStudyDashboard = new LiveStudyDashboard(OAuthToken, repoPath);
        liveStudyDashboard.checkParticipants();
        liveStudyDashboard.createBoard();
    }
}

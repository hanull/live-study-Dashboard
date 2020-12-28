import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final String OAuthToken = "유저 토큰";
        final String repoPath = "whiteship/live-study";

        LiveStudyDashboard liveStudyDashboard = new LiveStudyDashboard(OAuthToken, repoPath);
        liveStudyDashboard.checkParticipants();
        liveStudyDashboard.createBoard();
    }
}

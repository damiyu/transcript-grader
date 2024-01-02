package src;

public class Grader {
    public static void main(String[] args) {
        Transcript transcript = new Transcript();
        ToolFrame tools = new ToolFrame(transcript);

        if (args.length == 1 && args[0].compareTo("frame") == 0) {
            // Update the transcript with a JFrame.
            tools.launchFrame();
        } else {
            // Update the transcript on the command line.
            transcript.gradeScan(true, null);
        }
    }
}
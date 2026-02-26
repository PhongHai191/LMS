package utils;

public class GetFileName {

    public String getFileName(String file) {
        int cnt = 0;
        for (int i = 0; i < file.length(); i++) {
            if (file.charAt(i) == '\\') {
                cnt = i;
            }
        }
        return file.substring(cnt + 1);
    }
}

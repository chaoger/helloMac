import java.io.File;

public class Main {

    public static void main(String[] args) {

        egg(5,100);
    }

    public static  void egg(int K,int N){
        int m;
        int[] dp = new int[K+1];
        for (m = 0; dp[K]    < N; m++){
                for (int k = K; k > 0; --k)
                    dp[k] += dp[k - 1] + 1;
            for(int i = 0;i<K+1;i++){
                System.out.print(dp[i]+",");
            }
            System.out.println();

        }

    }


    public static int firstUniqChar(String s) {
        int arr[] = new int[26];
        for (int i=0;i<26;i++) {
            arr[i] = -1;
        }

        for(int i = 0 ;i<s.length();i++){
            if(arr[s.charAt(i)-'a'] == -1){
                arr[s.charAt(i)-'a'] = i;
            }else{
                arr[s.charAt(i)-'a'] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0;i<26;i++){
            System.out.print(arr[i]+",");
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0;i<26;i++){
            if(arr[i]!=-1&&arr[i]<res){
                res = arr[i];
            }
        }
        return res;

    }


    /**
     * 修改文件名
     * @param oldFilePath 原文件路径
     * @param newFileName 新文件名称
     * @param overriding 判断标志(如果存在相同名的文件是否覆盖)
     * @return
     */
    public static boolean renameFile(String oldFilePath,String newFileName,boolean overriding){
        File oldfile = new File(oldFilePath);
        if(!oldfile.exists()){
            System.out.println(1);
            return false;
        }
        String newFilepath = oldfile.getParent()+File.separator+newFileName;
        File newFile = new File(newFilepath);
        if(!newFile.exists()){
            System.out.println(2);
            return oldfile.renameTo(newFile);
        }else{
            if(overriding){
                newFile.delete();
                System.out.println(3);

                return oldfile.renameTo(newFile);
            }else{
                return false;
            }
        }
    }
}

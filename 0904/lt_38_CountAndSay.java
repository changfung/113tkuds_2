class Solution {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j <= res.length(); j++) {
                if (j < res.length() && res.charAt(j) == res.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count).append(res.charAt(j - 1));
                    count = 1;
                }
            }
            res = sb.toString();
        }
        return res;
    }
}

/*
解題思路：
1. 初始字串為 "1"。
2. 每次讀前一個字串，將相同的數字進行計數，轉換成 "次數 + 數字"。
3. 重複 n-1 次即可得到答案。
*/

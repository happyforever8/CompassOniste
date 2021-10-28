给你一个字典，比如「apple, app, bea, bbe, .....」，
输入一个词，如果词存在于字典中， 返回该词，如果不存在，
返回和这个词只有一个距离的词，一个距离的定义是：多一个字母，少一个字母、或修改一个字母

public class FindDistanceOneLetter {

    public static void main(String[] args) {
        helper(Arrays.asList("apple", "app", "aeppl", "appj"), "appl").forEach(a -> System.out.println(a));
    }

    public static List<String> helper(List<String> dict, String target) {

        List<String> res = new ArrayList<>();
        if (dict.contains(target)) {
            res.add(target);
        }

        for (String str : dict) {
            if (str.length() >= target.length() + 2 || str.length() <= target.length() - 2) continue;

            for (int i = 0; i < target.length(); i++) {
                if (str.length() > i && str.charAt(i) != target.charAt(i)) {
                    if (isMatch(target, str.substring(0, i) + str.substring(i + 1)) ||
                            isMatch(target, str.substring(0, i) + String.valueOf(target.charAt(i)) + str.substring(i)) ||
                            isMatch(target, str.substring(0, i) + String.valueOf(target.charAt(i)) + str.substring(i + 1))) {
                        res.add(str);
                    }
                    break;
                } else if (i >= str.length() && target.length() - str.length() == 1) {
                    if (isMatch(target, new StringBuffer(str).append(target.charAt(i)).toString())) {
                        res.add(str);
                    }
                    break;
                }
            }

            if (!res.contains(str) && str.length() - target.length() == 1) {
                if (isMatch(target, new StringBuilder(str).deleteCharAt(str.length() - 1).toString()))
                    res.add(str);
            }
        }

        return res;
    }

    public static boolean isMatch(String target, String input) {
        return target.equals(input);
    }
}


willwillzhang 发表于 2020-09-01 19:21:46
第二题， brute force了一个，有没有更好的方法呢？

public class FindDistanceOneLetter {
这题主要考点还是系统的思想。与其每次query都消耗很多时间，不如一次性消耗时间遍历字典array。
然后建立起hashmap。hashmap的key是target word，value是一个list，包含了和它相差一位的词。
这样以后query就成了O(1)复杂度了。string 建立起来的hashmap占用不了多少内存的。完全可行。
我的做法是建了三个hashmap。一个专门存多一位。一个专门存少一位。一个专门存修改一位。
没什么特别原因。只是看起来清晰点。只建一个hashmap包含三种应该也行。


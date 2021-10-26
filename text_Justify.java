Skip to content
Search or jump to…
Pull requests
Issues
Marketplace
Explore
 
@happyforever8 
happyforever8
/
Compass
Public
1
00
Code
Issues
Pull requests
Actions
Projects
Wiki
Security
Insights
Settings
Compass/Justify Text.java /
@happyforever8
happyforever8 Update Justify Text.java
Latest commit da1636b 3 days ago
 History
 1 contributor
368 lines (290 sloc)  11.8 KB
   
Multi-problem stack:


Wrap Lines:

Pt.1 Connecting words with '-' as blank spaces, no exceeds maxLength

Input: String[] words, int maxLength.
Output: List lines.
    e.g. ["1p3acres", "is", "a", "good", "place", "to", "communicate"], 12 => {"1p3acres-is", "a-good-place", "for", "communicate"}
复制代码Java Solution： O(n) time, O(n) space
public static List<String> wrapLines1(String[] words, int maxLength){
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int p = 0;
    while(p < words.length){
        if(sb.length() == 0)
            // assume all words length no exceed to maxLength
            sb.append(words[p++]);

        else if(sb.length() + 1 + words[p].length() <= maxLength){
            sb.append('-');
            sb.append(words[p++]);
        }
        else{
            ans.add(sb.toString());
            sb.setLength(0);
        }
    }
    if(sb.length() != 0) ans.add(sb.toString());
    return ans;
}
复制代码
Pt.2 Require every line to be "balanced".

Input: String[] lines, ["the way it moves like me", "another sentence example",...], int maxLength.
Output: List lines.
e.g. ["123 45 67 8901234 5678", "12345 8 9 0 1 23"], 10 => {"123--45-67", "8901234", "5678-12345", "8-9-0-1-23"}
["123 45 67 8901234 5678", "12345 8 9 0 1 23"], 15 => {"123----45----67", "8901234----5678", "12345---8--9--0", "23"}
复制代码Java Solution: O(n^2) worst time, O(n) space
public static List<String> wrapLines2(String[] lines, int maxLength){
    List<String> unbalanced = new ArrayList<>();
    List<String> words = new ArrayList<>();
    for(String line : lines){
        String[] word_collection = line.split(" ", -1);
        Collections.addAll(words, word_collection);
    }
    StringBuilder sb = new StringBuilder();
    int p = 0;
    while(p < words.size()){
        if(sb.length() == 0)
            // assume all words length no exceed to maxLength
            sb.append(words.get(p++));

        else if(sb.length() + 1 + words.get(p).length() <= maxLength){
            sb.append('-');
            sb.append(words.get(p++));
        }
        else{
            unbalanced.add(sb.toString());
            sb.setLength(0);
        }
    }
    if(sb.length() != 0) unbalanced.add(sb.toString());
    
    //now we have un-balanced result, then balance it
    List<String> balanced = new ArrayList<>();
    for(String line : unbalanced){
        StringBuilder cur_line = new StringBuilder(line);
        int num_needed = maxLength - cur_line.length();
        if(!cur_line.toString().contains("-")){
            balanced.add(cur_line.toString());
            continue;
        };
        while(num_needed > 0){
            int i = 0;
            while(i < cur_line.length() - 1){
                if(cur_line.charAt(i) == '-' && cur_line.charAt(i + 1) != '-'){
                    cur_line.insert(i + 1, '-');
                    num_needed--;
                    i++;
                    if(num_needed == 0) break;
                }
                i++;
            }
        }
        balanced.add(cur_line.toString());
    }
    return balanced;
}
复制代码
Pt.3 Assuming only one "-" between words, but define "score": 
sum of difference square between each line length and the length of longest
line (like variance), how to wrap can minimize the score. How long for each line is not limited. (dp)


















Coding challenge done with Karat (a 3rd party interviewing company).

Write a function to output fully justified text.
 The output must be an array of lines, and each line must have length equal to "lineLength" parameter - except if it's just one word. See Examples:
# Example 1 input
text = [ "Some modern typesetting programs",
          "offer four justification",
          "options" ]
lineLength = 24
# Your function, justify(text, lineLength)
# should return:
       [ "Some  modern typesetting",
         "programs    offer   four",
         "justification    options" ]
Within the same line, the amount of spaces between words should differ by no more than 1 space:
Not Allowed: "the      quick brown fox"
    Allowed: "the   quick   brown  fox"
Example 2:
# input
text = [ "The Earth is",
         "the only world",
         "known so far",
         "to harbor life" ]
lineLength = 18
# Return:
       [ "The  Earth  is the",
         "only  world  known",
         "so  far  to harbor",
         "life" ]
Example 3:
# input
text = [ "It underscores our responsibility",
         "to deal more kindly with one another" ]
lineLength = 15
# Return:
       [ "It  underscores",
         "our",
         "responsibility",
         "to   deal  more",
         "kindly with one",
         "another" ]
class Solution {
    public static void main(String[] args) {
        String[] text1 = {"It underscores our responsibility",
                "to deal more kindly with one another"};
        int lineLength1 = 15;
        String[] text2 = {"The Earth is",
                "the only world",
                "known so far",
                "to harbor life"};
        int lineLength2 = 18;
        String[] text3 = {"Some modern typesetting programs",
                "offer four justification",
                "options"};
        int lineLength3 = 24;
//
        System.out.println(new Solution().justifyText(text1, lineLength1));
        System.out.println(new Solution().justifyText(text2, lineLength2));
        System.out.println(new Solution().justifyText(text3, lineLength3));
    }
    private List<String> justifyText(String[] text, int lineLength) {
        //Collect all words
        List<String> words = new ArrayList<>();
        for (String line : text) {
            Arrays.asList(line.split(" ")).forEach(word -> words.add(word.trim()));
        }
        List<String> result = new ArrayList<>();
        List<String> toForm = new ArrayList<>();
        int count = 0;
        int size = 0;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (count + word.length() + 1 <= lineLength + 1) {
                toForm.add(word);
                count = count + word.length() + 1;
                size += word.length();
            } else {
                result.add(createLine(toForm, size, lineLength));
                toForm.clear();
                count = 0;
                size = 0;
                i--;
            }
        }
        if (!toForm.isEmpty()) {
            result.add(createLine(toForm, size, lineLength));
        }
        for (String str : result) {
            System.out.println(str);
        }
        System.out.println("\n------------------------\n");
        return result;
    }
    private String createLine(List<String> toForm, int totalLength, int lineLength) {
        if (toForm.size() == 1) {
            return toForm.get(0).trim();
        }
        int spaceRequired = lineLength - totalLength;
        int space = spaceRequired / (toForm.size() - 1);
        int restSpace = spaceRequired % (toForm.size() - 1);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < toForm.size(); i++) {
//            if (i == toForm.size() - 2) { //add rest space
//                appendSpace(restSpace, stringBuilder);
//            }
            stringBuilder.append(toForm.get(i));
            if (i == (toForm.size() - 1)) {
                break; // in this case we do not want to append space
            }
            appendSpace(space, stringBuilder);
            if (restSpace-- > 0)  // distribute the space as required.
                appendSpace(1, stringBuilder);
        }
        return stringBuilder.toString();
    }
    private void appendSpace(int space, StringBuilder stringBuilder) {
        while (space-- > 0) {
            stringBuilder.append(" ");
        }
    }
}
***********************Leetcode 
Given an array of strings words and a width maxWidth, format the text such 
that each line has exactly maxWidth characters and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words
as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
Extra spaces between words should be distributed as evenly as possible.
    If the number of spaces on a line does not divide evenly between words,
the empty slots on the left will be assigned more spaces than the slots on the right.
For the last line of text, it should be left-justified and no extra space is inserted between words.
Note:
A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]

class Solution {
//     Note:

// (1)A word is defined as a character sequence consisting of non-space characters only.
// (2)Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
// (3)The input array words contains at least one word.
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        
// 第一种是添加了当前单词后也不溢出行长度要求，
//      这时候就直接放进来；
// 第二种就是加进来当前单词后就正好是行长度，
//      这时候也可以直接放进来，不过需要再把缓冲区内容放到返回值中去；
// 第三种情况就比较复杂了，需要调整空格位置和数量。
// timeComplexity is O(N)
// space Complexity is O(N)

         List<String> list = new ArrayList<>();
        //position of first word in line
        int first = 0;
        while (first < words.length) {
            int width = words[first].length();
            //position of last word in line
            int last = first + 1;
            // the reason here is +1 is because we need a space between two words
            while (last < words.length && width + words[last].length() + 1 <= maxWidth) {
                width += words[last++].length() + 1;
            }
            
            StringBuilder sb = new StringBuilder(maxWidth);
            
            // everyt character needs a space
            int numOfSpacer = last - first - 1;
            
            //last line or one word in a line, left-justified
            if (last == words.length || numOfSpacer == 0) {
                sb.append(words[first]);
                
                for (int i = first + 1; i < last; i++) {
                    sb.append(" ").append(words[i]);
                }
                
                // this is for the last line, to add the space , 
                // for example ,"justification.  "]
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                // the space ammount adding to existing space
                int spaces = (maxWidth - width) / numOfSpacer;
                //extra spaces add to left spacers
                int extra = (maxWidth - width) % numOfSpacer;
                
                for (int i = first; i < last - 1; i++) {
                    sb.append(words[i]);
                    // Extra spaces between words should be 
                    //     distributed as evenly as possible. 
                    //     If the number of spaces on a line does 
                    //     not divide evenly between words,
                    // the empty slots on the left will be assigned more spaces than the slots on the right.
                    // this is to add extra space to the left
                    for (int j = 0; j <= spaces + ((i - first) < extra ? 1 : 0); j++) {
                        sb.append(" ");
                    }
                }
                sb.append(words[last - 1]);
            }
            list.add(String.valueOf(sb));
            first = last;
        }
        return list;
    }
}

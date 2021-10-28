
第二轮 算法
  // Goal: use the knownAddresses to build a data structure and/or function that allows
  //        us to assign a zipcode to addresses on Broadway that are missing them
  //        when we have enough information.
  //        Return the zipcode if we can figure out what it is based on the info we have,
  //        otherwise return null or an empty string
  //
  // Assumptions:
  //
  // 1. if 2 addresses are on the same street in the city, state, and zipcode,
  //    all addresses between them on that street are in the same zipcode
  //    (in other words, zipcodes are continuous along streets)
  // 2. nothing we care about is case sensitive
  // 3. we are allowed to modify known_addresses ahead of time however we want
  //    (ex: rearrange the list, different data structure, delete elements)
  // 4. All street numbers and zipcodes are valid positive integers
  // 5. We only need to return results for Broadway, New York NY;
  //    addresses on other streets can return null or an empty string

example：
给定一个list [["11", "Broadway", "New York", "NY", "11101"], 
          ["32", "Broadway", "New York", "NY", "11101"],
          ["145", "Broadway", "New York", "NY", "11102"],
          ["360", "BROADWAY", "NEW YORK", "NY", "11102"]]
input: "20 Broadway New york ny" 要output: 11101

          
例如, input = [[1, broadway, new york, NY, 10010], 
             [10, broadway, new york, NY, 10010], 
             [55, broadway, new york, CA, 98876], 
             [178, broadway, new york, NY, 10011],
             [199, broadway, new york, NY, 10011]]. 
          比如query是[3, BROADWAY, New YoRk, ny], 
就要输出10010. 如果输入是[11, BROADWAY, New YoRk, ny]或者[3, BROADWAY, New YoRk, CA]就要输出“”或null。


import java.util.*;
public class HelloWorld{

     public static void main(String []args){
        System.out.println("Hello World");
        List<String> list1 = Arrays.asList("11", "Broadway", "New York", "NY", "11101");
        List<String> list2 = Arrays.asList("32", "Broadway", "New York", "NY", "11101");
        List<String> list3 = Arrays.asList("145", "Broadway", "New York", "NY", "11102");
        List<String> list4 = Arrays.asList("360", "BROADWAY", "NEW YORK", "NY", "11102");
        
        List<List<String>> input = new ArrayList<>();
        
        input.add(list1);
        input.add(list2);
        input.add(list3);
        input.add(list4);
        
        String target = "65,Broadway,New york,ny";
        
        System.out.println(findZipCode(input, target));
     }
     
     public static String findZipCode(List<List<String>> input, String target){
         // key is address
          // value key is zipcode, values is the street number list
         Map<String, Map<String, Address>> map = new HashMap<>();
         
         for (List<String> list : input){
                 
                 String addressKey = list.get(1) + list.get(2) + list.get(3);
                 addressKey = addressKey.toLowerCase();
                 String zipCode = list.get(4);
                 
                 map.putIfAbsent(addressKey, new HashMap<>());
                 
                 
                 if (map.containsKey(addressKey)){
                     Map<String, Address> curr = map.get(addressKey);
                     
                     if (curr.containsKey(zipCode)){
                          curr.get(zipCode).start = Math.min(Integer.parseInt(list.get(0)),  curr.get(zipCode).start);
                          curr.get(zipCode).end = Math.max(Integer.parseInt(list.get(0)),  curr.get(zipCode).end);
                     } else {
                         Address address = new Address(Integer.parseInt(list.get(0)),Integer.parseInt(list.get(0)));
                         map.get(addressKey).put(zipCode, address);
        
                     }
                    
                 } else {
                     Address address = new Address(Integer.parseInt(list.get(0)),Integer.parseInt(list.get(0)));
                     map.get(addressKey).put(zipCode, address);
                 }
         }
          
         String[] strs = target.split(",");
         
         String targetString = strs[1] + strs[2] + strs[3];
         targetString = targetString.toLowerCase();
        if (!map.containsKey(targetString)){
             return "did not find address";
        }
             
        Map<String, Address> currMap = map.get(targetString);
             
             
        for ( Map.Entry<String, Address> entry: map.get(targetString).entrySet()){
                 
             int tempStart = entry.getValue().start;
            int tempEnd = entry.getValue().end;
             
            if (tempStart <= Integer.parseInt(strs[0]) && tempEnd >= Integer.parseInt(strs[0])){
                return entry.getKey();
                     
            }
   
        }

         return "did not find";
         
     }
     static class Address{
         int start;
         int end;
        // String zipCode;
         
         public Address(int start, int end){
             this.start = start;
             this.end = end;
            // this.zipCode = zipCode;
         }
     }
}


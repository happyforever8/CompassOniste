
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
          ["32", "Broadway", "New York", "NY", "11101"], ["145", "Broadway", "New York", "NY", "11102"],["360", "BROADWAY", "NEW YORK", "NY", "11102"]]
input: "20 Broadway New york ny" 要output: 11101

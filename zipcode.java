白人Coding：问了地里经典的题，输入是vector<vector<string>>，
每个subvector长这样：[id, street, city, state, zipcode]，且输入合法。
问：给你一个Broadway, new york, NY 的id，判断出他的zipcode是什么。如果无法判断则输出“”或null。
例如, input = [[1, broadway, new york, NY, 10010], [10, broadway, new york, NY, 10010],
             [55, broadway, new york, CA, 98876], [178, broadway, new york, NY, 10011], 
             [199, broadway, new york, NY, 10011]]. 比如query是[3, BROADWAY, New YoRk, ny], 
就要输出10010. 如果输入是[11, BROADWAY, New YoRk, ny]或者[3, BROADWAY, New YoRk, CA]就要输出“”或null。

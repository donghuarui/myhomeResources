2018年12月28日23:32:38
1.在addInterceptors方法中添加拦截器时，是new的一个拦截器。而并不是引入springBean容器初始化时，已经初始化好了的bean实例，
并不是同一个对象，故而访问时肯定是无法引入Interceptor类里面引入的bean实例，可能产生空指针异常。即无法引入默认注入的Bean，
那么我们就自己定义一个Bean，然后在拦截器添加方法内引入这个bean即可，上面主要对此进行了处理。

2020年2月12日21:47:10
redis相关：
//        redisTemplate.opsForValue().set("myname",666);
//        String s = redisTemplate.opsForValue().get("dubbo")+"";
//        String a =   redisTemplate.opsForHash().get("dubbo","name")+"";
//         Map<Object,Object> map = redisTemplate.opsForHash().entries("dubbo");
//        System.out.println(map);
//        System.err.print(s);
//        logger.info(s);

2020年3月4日10:01:55
git修改提交日期
博客地址: https://blog.csdn.net/lindexi_gd/article/details/103184856
git commit --date="Feb 20 9:05:20 2020 +0800" -am "提交"
January, Jan.
February, Feb.
March, Mar.
April, Apr.
May, May.
June, Jun.
July, Jul.
August, Aug.
September, Sep.
October, Oct.
November, Nov.
December, Dec.


2020年3月4日10:42:42
gitignore 忽略不生效的问题  
博客地址: https://blog.csdn.net/jack_nichao/article/details/76382782
git rm -r --cached .
git add .
git commit -m 'update .gitignore'

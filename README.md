# 工具类总结
<ul>
  <li>utils_base:所有工具类的基础包，包括HttpUtils、Logs、RandomUtils、StrUtils、TimerUtils、TimeUtils、UUIDUtils</li>
  <li>utils_secret:加密工具，包括AES工具和MD5工具</li>
  <li>utils_app:android开发基础类，定义了一系列常用的工具类和方法</li>
  <li>utils_app_rx:android开发所用的OkHttp + Retrofit + RxJava</li>
</ul>
<h3>RxBus（一）</h3>
<p>&nbsp;&nbsp;&nbsp;&nbsp;如果已经在项目里使用了RxJava了，可以考虑实现RxBus来替代EventBus等库，在实现相同功能的前提下，减少代码量。由于本人正式在项目里，使用RxJava时间比较晚，所以，Rx系列的文章，许多具体实现是参考了其他的blog，并加上自己的理解。但由于无法考证哪位是原作者，在此就不添加作者链接了，但还是对大牛们表示感谢。关于具体如何实现RxBus，在此不做具体描述，因为已经有很多的blog，都详细的讲解如何去实现RxBus，如果不清楚，可以自行搜索RxBus的实现或参考我的代码。本blog更多的是关注在如何在项目里去使用RxBus，以及关于Event方面的内容。</p>
<img src="" />

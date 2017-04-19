# 工具类总结
<ul>
  <li>utils_base:所有工具类的基础包，包括HttpUtils、Logs、RandomUtils、StrUtils、TimerUtils、TimeUtils、UUIDUtils</li>
  <li>utils_secret:加密工具，包括AES工具和MD5工具</li>
  <li>utils_app:android开发基础类，定义了一系列常用的工具类和方法</li>
  <li>utils_app_rx:android开发所用的OkHttp + Retrofit + RxJava</li>
</ul>
<h3>RxBus（一）</h3>
<p>&nbsp;&nbsp;&nbsp;&nbsp;如果已经在项目里使用了RxJava了，可以考虑实现RxBus来替代EventBus等库，在实现相同功能的前提下，减少代码量。由于本人正式在项目里，使用RxJava时间比较晚，所以，Rx系列的文章，许多具体实现是参考了其他的blog，并加上自己的理解。但由于无法考证哪位是原作者，在此就不添加作者链接了，但还是对大牛们表示感谢。关于具体如何实现RxBus，在此不做具体描述，因为已经有很多的blog，都详细的讲解如何去实现RxBus，如果不清楚，可以自行搜索RxBus的实现或参考我的代码。本blog更多的是关注在如何在项目里去使用RxBus，以及关于Event方面的内容。</p>
<img src="https://github.com/cily-code/Utils/blob/master/images/rxbus_1.png" />
<p>&nbsp;&nbsp;&nbsp;&nbsp;RxBus的代码暂时封装的比较简单，后期根据实际情况，可能会做进一步的封装，以便于使用更简单。在post方法里，可以发送任何对象，但我考虑的是代码统一维护，所以强制规定了只能发送Event类型的对象。本期的做法是定义一个没有任何成员变量、成员方法的基类，如果需要使用，则自定义一个Event子类，这样，在业务层处理Event的时候，所有的对象都是Event的子类。这种做法比较简单，也比较清晰，但在实际使用过程中，发现需要自定义N多个子类Event，繁琐。后面在使用Handler + Message的时候，这种模式，不就是我所需要的吗？于是就打算学习一个Message的做法，自行封装一个统一的Event。目前的Event定义了两个成员变量what和obj，所有的Event都发送Event对象，业务层处理的时候，都是处理Event对象，不再处理不同的子Event了；根据what区分是哪个事件，根据obj区分具体的事件内容</p>
<img src="https://github.com/cily-code/Utils/blob/master/images/rxbus_event_1.png" />
<p>&nbsp;&nbsp;&nbsp;&nbsp;但这只是一个最初的版本，只解决了需要重复定义Event的问题，后续我将参考Message的源码，加入对象池，以便于Event对象的复用</p>
<h3>RxBus（二）</h3>
<p>&nbsp;&nbsp;&nbsp;&nbsp;本文继续完成RxBus未完成的事情，学习Message源码，引入对象池，完成对Event对象的复用</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;对象复用，个人粗浅理解，创建出一个对象池出来，不用的对象，加入到对象池里，下一次需要使用的时候，直接从池中取出来，避免重复创建和销毁对象带来的额外开销。关于对象池方面的知识，由于本人能力有限，在此就不详细展开了，需要详细了解，请自行搜索或查看api文档。对象池，有很多第三方包已经实现了，也可自行实现，我所采用的是android的v4包里提供的实现，具体路径为android.support.v4.util.Pools。该工具类提供了一个比较简单的对象池实现方法，使用也很简单。具体代码为：</p>
<img src="https://github.com/cily-code/Utils/blob/master/images/rxbus_event_2.png" />
<p>&nbsp;&nbsp;&nbsp;&nbsp;这样，就完成了对Event对象的复用了。到目前为止，已经完成了对象复用的工作了，基本也满足了正常的业务需求了。但如果深入研究源码，就会发现还有一些小问题。在v4包里提供的对象池的大小，是一个固定不可变了，如果对象数量超过了池的大小，就没法入池了。在utils_app下的Pools类里，我已经改成了动态改变池大小了，暂时还没有发现什么问题，如果有问题，欢迎提出来，谢谢！</p>

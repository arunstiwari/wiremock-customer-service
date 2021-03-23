# Socket Timeout exception
```java
java.net.SocketTimeoutException: Read timed out
```

```java
2021-03-23 09:32:09.674 ERROR 4646 --- [nio-8084-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8092/orders/customers/cust-2232": Read timed out; nested exception is java.net.SocketTimeoutException: Read timed out] with root cause

java.net.SocketTimeoutException: Read timed out
	at java.net.SocketInputStream.socketRead0(Native Method) ~[na:1.8.0_282]
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116) ~[na:1.8.0_282]
	at java.net.SocketInputStream.read(SocketInputStream.java:171) ~[na:1.8.0_282]
```
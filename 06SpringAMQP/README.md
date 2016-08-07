Building blocks of SpringRabbitMQ
- RabbitTemplate class provides convenient utility to publish messages or subscribe to RabbitMQ server.
- RabbitAdmin class provides utility to create/remove exchanges, queues, binding etc.
- listener containers provide mechanism to create listeners that bind to RabbitMQ message queue.

In this app, Send and receive message using App.java

App.java
- CachingConnectionFactory caches some channels to improve performance. Cache mode and size is configurable.
- Create a queue using RabbitAdmin.
- Send and receive message using RabbitTemplate
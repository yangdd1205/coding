## 多个消费者（相同 GroupID）订阅相同的 Topic 和 Tag

GroupID 相同者是视为同一个消费者，消息不会在同一个 GroupID 中出现重复消费。比如 MQ 里面有 MsgA。那么消费者 A 消费了，要么消费者 B 消费了。

## 多个消费者（不同 GroupID）订阅相同的 Topic 和 Tag

每个消费者都能拿到全量的数据。比如 MQ 里面有 MsgA 和 MsgB。两个消费者都能消费到 2 条消息。
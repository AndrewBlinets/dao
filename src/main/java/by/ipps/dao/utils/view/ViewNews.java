package by.ipps.dao.utils.view;

public class ViewNews {
  interface Base {}

  interface ShortNews {}

  interface LongNews {}

  interface AdminNews {}

  public static class BaseClass implements Base {}

  public static class ShortNewsClass implements ShortNews {}

  public static class LongNewsClass implements LongNews {}

  public static class AdminNewsClass implements AdminNews {}
}

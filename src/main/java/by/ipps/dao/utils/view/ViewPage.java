package by.ipps.dao.utils.view;

public class ViewPage {
  interface Base {}

  interface BaseContent {}

  interface BaseName {}

  interface Admin {}

  interface Section {}

  public static class BaseClassPage implements Base {}

  public static class BaseClassPageContent implements BaseContent {}

  public class BaseClassPageName implements BaseName {}

  public static class AdminClass implements Admin {}

  public static class SectionClass implements Section {}
}

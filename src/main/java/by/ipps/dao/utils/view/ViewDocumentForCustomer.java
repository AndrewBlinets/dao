package by.ipps.dao.utils.view;

public class ViewDocumentForCustomer {

  interface Base {}

  interface File {}

  public static class BaseClass implements ViewDocumentForCustomer.Base {}

  public static class FileClass implements File {}
}

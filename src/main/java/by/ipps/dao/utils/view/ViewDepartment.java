package by.ipps.dao.utils.view;

public class ViewDepartment {

  interface Base {}

  public static class BaseClassDepartment implements ViewDepartment.Base {}

  public static class FullInformationClassDepartment extends BaseClassDepartment {}
}

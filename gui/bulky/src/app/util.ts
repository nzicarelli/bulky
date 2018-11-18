export class Util {
  public static validateEmail(email) {
    console.log('VALID MAIL CHECK ' + email);
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  public static validateCell(cell) {
    if (!cell || cell.length <= 0) {
      return false;
    }
    if (cell.startsWith('3') && cell.length >= 9 && cell.length <= 10) {
      return true;
    }
    return false;
  }
}

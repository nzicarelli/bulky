import swal from 'sweetalert2';

export class Util {

  static alertInfo = 'info';
  static alertSuccess = 'success';
  static alertError = 'error';
  static alertWarning = 'warning';
  static alertQuestion = 'question';

// SweetAlertType = 'success' | 'error' | 'warning' | 'info' | 'question' | undefined;

  public static alert(): void {
    swal({
      text: 'testo ?',
      type: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'OK',
      cancelButtonText: 'Annulla',
    }).then((result) => {
        console.log('OK ' + result);
      },
      (dismiss) => {
        console.log('swal CHIUSA ');
      });
  }

  public static alertMsg(_type: any, _title: string, _text: string): void {
    swal({
      text: _text,
      title: _title,
      type: _type,
      showCancelButton: false,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'OK',
      cancelButtonText: 'Annulla',
    }).then((result) => {
        console.log('OK ' + result);
      },
      (dismiss) => {
        console.log('swal CHIUSA ');
      });
  }

  public static confirm(_title: string, _text: string, _type: any, callback: any ) {
    swal({
      title: _title,
      text: _text,
      type: _type,
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Conferma!'
    }).then((result) => {
      if (result.value) {
        callback();
      }
    })
  }

  public static showGenericMessageError() {
    this.alertMsg(this.alertError, 'Errore', 'Si è verificato un errore.');
  }

  public static confirmAdvanced(_title: string, _text: string, _type: any, confirmText: any, cancelText: any, callbackConfirm: any, callbackCancel: any ) {
    /*const swalWithBootstrapButtons = swal.mixin({
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false,
    });*/

    swal({
      title: _title,
      text: _text,
      type: _type,
      showCancelButton: true,
      confirmButtonText: confirmText,
      cancelButtonText: cancelText,
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        callbackConfirm();
      } else {
        if (result.dismiss) {
          callbackCancel();
        }
      }
    })
  }

  public static validateEmail(email) {
    console.log('VALID MAIL CHECK '+email);
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  public static validateCell(cell) {
    if (!cell || cell.length <= 0 ) {
      return false;
    }
    if ( cell.startsWith('3') && cell.length >= 9 && cell.length <=10) {
      return true;
    }
    return false;
  }


}

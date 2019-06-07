function makeAppConfig() {
  const date = new Date();
  const year = date.getFullYear();

  // const metas = window.document.getElementsByTagName('meta');
  // var baseApiUrl: any;
  // for (let i = 0; i < metas.length; i++) {
  //   if (metas[i].getAttribute('name') === 'content-root-api') {
  //     baseApiUrl = metas[i].getAttribute('content');
  //   }
  // }

  const AppConfig = {
    brand: 'BULKY-SYSTEM',
    copy: 'BULKY',
    user: {},
    year: year,
    productLink: 'http://www.bulky.it/',
    currentUser: 'currentUser',
    apiUrl: 'api/',
    token: '',
    record4page: 20,
    i18n: {},
    language: 'en',
    simulated: false,
    colors01: ['#41A5F3', '#906CCA', '#7CC47F', '#FDD147'],
    colors02: ['#003366', '#5288DB', '#99CC99', '#CF7F26'],
    filters: 'filters',
    areaGrp: false,
    user_role: {},
    apply_filter: true,
    tokenHeader:'Authorization',
    tokenPrefix:'Bearer '
  };
  return AppConfig;
}
export const APPCONFIG = makeAppConfig();

function makeAppConfig() {
    const date = new Date();
    const year = date.getFullYear();

    const AppConfig = {
        brand: 'Material',
        user: 'Lisa',
        year,
        layoutBoxed: false,               // true, false
        navCollapsed: false,              // true, false
        navBehind: false,                 // true, false
        fixedHeader: true,                // true, false
        sidebarWidth: 'middle',           // small, middle, large
        theme: 'light',                   // light, gray, dark
        colorOption: '34',                // 11,12,13,14,15,16; 21,22,23,24,25,26; 31,32,33,34,35,36
        AutoCloseMobileNav: true,         // true, false. Automatically close sidenav on route change (Mobile only)
        productLink: '#',
        tokenHeader: 'Authorization',
        tokenPrefix: 'Bearer ',
        pageSize: 25,
        customer: {},
        comune: '',
        indirizzo: 0
    };

    return AppConfig;
}

export const APPCONFIG = makeAppConfig();

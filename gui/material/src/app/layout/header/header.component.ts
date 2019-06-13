import { Component, OnInit } from '@angular/core';
import { APPCONFIG } from '../../config';
import { AuthenticationService } from '../../services/authentication.service';
import { User } from '../../user/user';

@Component({
    selector: 'my-app-header',
    styles: [],
    templateUrl: './header.component.html'
})

export class AppHeaderComponent implements OnInit {
    public AppConfig: any;
    public user: User;
    public notifications: any = [];

    constructor(private auth: AuthenticationService) {

    }

    ngOnInit() {
        this.AppConfig = APPCONFIG;
        this.user = this.auth.currentUserValue;
        // console.log('init AppHeaderComponent ');
        // console.log(this.user);
        this.notifications.push({
            icon: 'mail_outline',
            type: 'mail',
            text: 'Nuova Mail',
            data: new Date()
        });
    }
}

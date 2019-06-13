import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { AuthenticationService } from '../../services/authentication.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
    selector: 'my-page-login',
    styles: [],
    templateUrl: './login.component.html'
})

export class PageLoginComponent implements OnInit {
    private user: any;
    private returnUrl: string;

    constructor(private apiService: ApiService, private auth: AuthenticationService, private route: ActivatedRoute, private router: Router) {

    }

    ngOnInit() {
        this.auth.logout();
        this.user = {
            username: '',
            password: ''
        };
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    login() {
        console.log(this.user.username + ' '  + this.user.password);
        this.auth.login(this.user.username, this.user.password).subscribe(
            (data) => {
                console.log(data);
                this.router.navigate([this.returnUrl]);
            },
            (error) => {
                console.log(error);
            });
    }
}

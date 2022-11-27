import { Component, OnInit } from '@angular/core';
import UsernamePasswordCredentials from "../../model/username-password-credentials";
import { AuthService } from "../../service/auth/auth.service";
import JwtResponse from "../../model/jwt-response";
import cookie from "../../util/cookie";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public credentials: UsernamePasswordCredentials = {
    username: null,
    password: null
  }
  public authFailed: boolean

  constructor(private authService: AuthService) {
    this.authFailed = false
  }

  ngOnInit(): void {

  }

  submit() {
    const response = this.authService.login(this.credentials);
    if (response != null) {
      response.subscribe((response: JwtResponse) => {
        this.authService.setToken(response.token);
      }, (error: HttpErrorResponse) => {
        if (error.status == 401) this.authFailed = true;
      });
    }

  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { environment } from "../../../environments/environment";
import UsernamePasswordCredentials from "../../model/username-password-credentials";
import JwtResponse from "../../model/jwt-response";
import { CookieService } from "ngx-cookie-service";
import cookie from "../../util/cookie";
import authorizationType from "../../util/authorization-type";
import AuthMeResponse from "../../model/auth-me-response";
import { Observable } from "rxjs";
import { Router } from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private cookieService: CookieService,
              private router: Router) { }

  login(credentials: UsernamePasswordCredentials): Observable<JwtResponse> | null {
    if (credentials.username == null || credentials.password == null) {
      return null;
    }
    const token = btoa(credentials.username + ":" + credentials.password);
    return this.http.get<JwtResponse>(`${environment.api}/auth/login`, {
      headers: {
        'Authorization': `${authorizationType.basic} ${token}`
      }
    })
  }

  setToken(token: string) {
    this.cookieService.set(cookie.auth, token);
    this.router.navigateByUrl("/");
  }

  isLoggedIn() {
    return new Promise((resolve, reject) => {
      this.http.get<AuthMeResponse>(`${environment.api}/auth/me`)
        .subscribe((response: AuthMeResponse) => {
          resolve(response.loggedIn);
        }, (e: HttpErrorResponse) => {
          if (e.status == 401) this.router.navigateByUrl("/login");
        });
    })
  }
}

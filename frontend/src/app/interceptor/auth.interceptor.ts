import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from "ngx-cookie-service";
import cookie from "../util/cookie";
import authorizationType from "../util/authorization-type";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = this.cookieService.get(cookie.auth);
    if (token != null && request.headers.get("Authorization") == null) {
      request = request.clone({
        setHeaders: {'Authorization': `${authorizationType.bearer} ${token}`}
      });
    }

    return next.handle(request);
  }
}

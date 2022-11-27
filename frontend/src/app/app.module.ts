import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from "./component/login/login.component";
import { ClientAddComponent } from "./component/client/client-add/client-add.component";
import { ClientFormComponent } from "./component/client/client-form/client-form.component";
import { ClientEditComponent } from "./component/client/client-edit/client-edit.component";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { ClientListComponent } from './component/client/client-list/client-list.component';
import { CookieService } from "ngx-cookie-service";
import { FormsModule } from "@angular/forms";
import { AuthInterceptor } from "./interceptor/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ClientAddComponent,
    ClientFormComponent,
    ClientEditComponent,
    ClientListComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
  ],
  providers: [CookieService, {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

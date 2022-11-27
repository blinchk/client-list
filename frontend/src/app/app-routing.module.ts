import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./component/login/login.component";
import { ClientAddComponent } from "./component/client/client-add/client-add.component";
import { ClientEditComponent } from "./component/client/client-edit/client-edit.component";
import { ClientListComponent } from "./component/client/client-list/client-list.component";
import { AuthGuard } from "./guard/auth/auth.guard";

const routes: Routes = [
  { path: 'login', component: LoginComponent, title: 'Login' },
  { path: 'add', component: ClientAddComponent, title: 'Add', canActivate: [AuthGuard] },
  { path: ':id', component: ClientEditComponent, title: 'Edit', canActivate: [AuthGuard]  },
  { path: '**', component: ClientListComponent, title: 'Clients', canActivate: [AuthGuard]   }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [AuthGuard],
  exports: [RouterModule]
})
export class AppRoutingModule { }

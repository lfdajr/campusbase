import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPage } from './pages/login/login.page';
import { PageNotFoundComponent } from './pages/notfound/not-found.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPage },
  {
    path: 'turmas',
    loadChildren: './pages/turmas/turmas.module#TurmasModule'
  },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: false})],
  exports: [RouterModule]
})
export class AppRoutingModule { }


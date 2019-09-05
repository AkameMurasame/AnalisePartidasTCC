import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UsuarioModule } from './modules/usuario/usuario.module';
import { SharedModule } from './modules/shared/shared.module';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http'; 
import { DashboardComponent } from './modules/usuario/components/dashboard/dashboard.component';
import { SiteComponent } from "./components/site/site.component";
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './helpers/jwt.interceptor';
import { ErrorInterceptor } from './helpers/error.interceptor';
import { routing } from './RoutesApp';
import { NgxUiLoaderModule, NgxUiLoaderHttpModule, NgxUiLoaderRouterModule } from 'ngx-ui-loader';
import { ErrorComponent } from './modules/shared/components/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SiteComponent,
    NavBarComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    UsuarioModule,
    NgxUiLoaderModule,
    NgxUiLoaderHttpModule,
    NgxUiLoaderRouterModule,
    BrowserAnimationsModule,
    SharedModule,
    NgbModule,
    HttpClientModule,
    routing,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

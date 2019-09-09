import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const currentUser = this.authenticationService.currentUserValue;

        if (!(currentUser.valido === 'S')) {
            this.router.navigate(['cadastro/invocador']);
            return false;
        }

        if (currentUser) {
            //se estiver logado, retorna true
            return true;
        }

        //Redireciona para a pagina inicial, quando não estiver logado
        this.router.navigate(['/']);
        return false;
    }
}
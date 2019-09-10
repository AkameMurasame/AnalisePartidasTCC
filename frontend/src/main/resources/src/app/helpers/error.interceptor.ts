import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from '../services/authentication.service';
import { ModalService } from '../modules/shared/components/modal/modal.service';
import { ErrorComponent } from '../modules/shared/components/error/error.component';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService, private modalService: ModalService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            //401 Unauthorized
            if (err.status === 401) {
                this.modalService.openModal(ErrorComponent);
                //this.authenticationService.logout();
                //window.location.reload(true);
            }
            return throwError(err.error);
        }));
    }
}

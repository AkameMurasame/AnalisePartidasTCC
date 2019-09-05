import { AuthenticationService } from '../services/authentication.service';

export class IsAuthGuard {
    constructor(private authenticationService: AuthenticationService) { }

    IsLogado() {
        const currentUser = this.authenticationService.currentUserValue;
        if (currentUser) {
            return true;
        } else {
            return false;
        }
    }
}
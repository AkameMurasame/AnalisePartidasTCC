import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../models/user';
import { AppConfig } from '../AppConfig';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UsuarioServices {

    constructor(private http: HttpClient) {

    }

    registroUsuario(user: User): Observable<User> {
        return this.http.post<User>(`${AppConfig.API_ENDPOINT}/usuario/cadastro`, user)
            .pipe(map(usuario => {
                return usuario;
            }));
    }
}

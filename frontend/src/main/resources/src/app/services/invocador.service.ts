import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Invocador } from '../models/invocador.class';
import { AppConfig } from '../AppConfig';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class InvocadorService {

    private _invocador: Invocador;

    constructor(private http: HttpClient) {

    }

    public get Invocador() {
        return this._invocador;
    }

    public set  Invocador(invocador: Invocador) {
        this._invocador = invocador;
    }

    registrarInvocador(nickname: string): Observable<Invocador> {
        return this.http.get<Invocador>(`${AppConfig.API_ENDPOINT}/invocador/validar/${nickname}`)
            .pipe(map(summoner => {
                return summoner;
            }));
    }
}

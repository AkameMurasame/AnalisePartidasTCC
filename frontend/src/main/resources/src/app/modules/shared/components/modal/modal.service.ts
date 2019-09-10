import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ModalService {

    openModal(modalComponent) {
        new modalComponent().openModal();
        //modalComponent.openModal();
    }
}

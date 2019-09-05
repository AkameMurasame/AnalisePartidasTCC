import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { ModalComponent } from '../modal/modal.component';
import { ConfigModal } from '../modal/configModal';

@Component({
  selector: 'error-component',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {

  @ViewChild(ModalComponent, { static: true })
  modal: ModalComponent;

  configModal: ConfigModal;

  @Input()
  type: string;

  @Input()
  error: string;

  cssFooter = null;
  cssHeader = null;
  cssBody = "modal-body";
  textHeader = null;

  constructor() {
    this.configModal = new ConfigModal(this.cssFooter, this.cssHeader, this.cssBody, this.textHeader);
  }

  ngOnInit() {
    this.modal.openLg();
  }

}

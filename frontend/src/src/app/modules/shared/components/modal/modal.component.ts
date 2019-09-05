import { Component, EventEmitter, Output,ViewChild, ElementRef, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfigModal } from './configModal';

@Component({
  selector: 'modal-component',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent {

  @Output()
  onSave: EventEmitter<void> = new EventEmitter<void>();

  @ViewChild('content', {static: false}) 
  content: ElementRef;

  @Input()
  configModal: ConfigModal;

  constructor(private modalService: NgbModal) {   }

  ngOnInit() {
  }

  closeModal() {
    this.modalService.dismissAll();
  }

  openBackDropCustomClass() {
    this.modalService.open(this.content, { backdropClass: 'light-blue-backdrop' });
  }

  openWindowCustomClass() {
    this.modalService.open(this.content, { windowClass: 'dark-modal' });
  }

  openSm() {
    this.modalService.open(this.content, { size: 'sm' });
  }

  openLg() {
    this.modalService.open(this.content, { size: 'lg' });
  }

  openXl() { this.modalService.open(this.content, { size: 'xl' }); }

  openVerticallyCentered() {
    this.modalService.open(this.content, { centered: true });
  }

  openScrollableContent() {
    this.modalService.open(this.content, { scrollable: true });
  }

  onSaveEvent(event, modal) {
    this.onSave.emit()
  }
}

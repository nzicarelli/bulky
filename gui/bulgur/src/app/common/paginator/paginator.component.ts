import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.scss']
})
export class PaginatorComponent implements OnInit {

  @Input() maxSize = 0;
  @Input() record4page: number;
  @Input() totalRecord: number;
  @Input() currentPage: number;

  @Output() pageChange: EventEmitter<number> = new EventEmitter<number>();
  numPage: number;
  pages:any=[];

  constructor() {
  }

  ngOnInit() {
    if (!this.record4page || this.record4page ==0){
      this.record4page = 10;
    }
    if (!this.maxSize || this.maxSize ==0){
      this.maxSize = 5;
    }
    this.numPage = ~~(this.totalRecord/this.record4page);
    this.maxSize = Math.min(this.numPage,this.maxSize);
    for(let i=0; i<this.maxSize; i++){
      this.pages.push(i);
    }
  }

  goPage(current: number) {
    this.currentPage = current;
    this.emit();
  }
  goNext(){
    if (this.currentPage<(this.numPage-1)) {
      this.currentPage++;
      this.refreshPages();
      this.emit();
    }
  }

  goPrev(){
    if (this.currentPage>0) {
      this.currentPage--;
      this.refreshPages();
      this.emit();
    }
  }

  private emit() {
    console.log('emit '+this.currentPage);
    if (this.currentPage<(this.numPage-1)) {
      this.pageChange.emit(this.currentPage);
    }
  }

  private refreshPages() {
    if (!this.contains(this.pages,this.currentPage)){
      const middle = ~~(this.maxSize/2);
      if ( (this.currentPage+middle)<=this.numPage ){
        const start = this.currentPage-middle;
        this.pages = [];
        for(let i = start; i< (start+this.maxSize); i++ ){
          this.pages.push(i);
        }
      }
    }
  }
  private contains(arr:any, item:any):boolean{
    for (const x of arr){
      if (x == item){
        return true;
      }
    }
    return false;
  }
}

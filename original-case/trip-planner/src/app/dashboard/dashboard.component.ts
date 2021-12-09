import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  totalReqProcessed = 0;
  OkResponses = 0;
  FourXXResponses = 0;
  FiveXXResponses = 0;
  averageResponseTimeForAllRequests = 0;
  minResponseTime = 0;
  maxResponseTime = 0;

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.callGetApiStats();
  }

  callGetApiStats() {
    console.log("callGetApiStats");
    this.dataService
    .getApiStats()
    .subscribe((data: any)=>{
      console.log(data);

      this.totalReqProcessed = data.totalReqProcessed;
      this.OkResponses = data.okResponses;
      if( data.fourXXResponses != null ) {
        this.FourXXResponses = data.fourXXResponses;
      }
      if( data.fiveXXResponses != null ) {
        this.FiveXXResponses = data.fiveXXResponses;
      }
      this.averageResponseTimeForAllRequests = data.averageResponseTimeForAllRequests;
      this.minResponseTime = data.minResponseTime;
      this.maxResponseTime = data.maxResponseTime;
    },
    error => {
      console.error('Error: ' + error);
    })
  }
}

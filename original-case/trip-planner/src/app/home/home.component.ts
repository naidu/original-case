import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { DataService } from '../data.service';

@Component({
  selector: 'Airports',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {

  originFormControl = new FormControl('');
  desginationFormControl = new FormControl('');
  fareFormControl = new FormControl('');
  airports: string[] = [];
  
  constructor(private dataService: DataService) { }
  
  ngOnInit() {
    console.log("ngOnInit");
    this.callGetAirportsAPI();
  }
  
  callFaresAPI() {
    console.log("callFaresAPI");
    this.dataService
    .getFare(this.originFormControl.value, this.desginationFormControl.value)
    .subscribe((data: any)=>{
      console.log(data);
      console.log("callGetAirportsAPI faire = " + data.amount);
      this.fareFormControl.setValue(data.amount);
    },
    error => {
      console.error('Error: ' + error);
    })
  }
  
  callGetAirportsAPI() {
    console.log("callGetAirportsAPI");
    
    this.dataService
    .getAirports()
    .subscribe((data: any)=>{
      console.log("number of elements: " + data.page.size);
      console.log(data);
      this.airports = data._embedded.locations as string[];
      console.log("number of airports: " + this.airports.length);
      console.log(this.airports[0]);
    },
    error => {
      console.error('Error: ' + error);
    })
  }
}

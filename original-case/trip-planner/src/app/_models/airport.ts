export class Coordinates
{ 
  constructor(
    public latitude: number,
    public longitude: number
  ) {}
}

export class Airport {
  constructor(
    public code: string,
    public name: string,
    public description: string,
    public coordinates: Coordinates
  ) {  }
}

export interface Airports {
  [key: string]: any;
}
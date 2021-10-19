import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlacesListComponent } from './components/places-list/places-list.component';

const routes: Routes = [
  { path: 'nearby-places', component: PlacesListComponent },
  { path: '', redirectTo: 'nearby-places', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

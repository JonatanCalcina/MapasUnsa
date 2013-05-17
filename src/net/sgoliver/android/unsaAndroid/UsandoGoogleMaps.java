package net.sgoliver.android.unsaAndroid;

import net.sgoliver.android.unsaAndroid.R;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

//Lo primero que se debe hacer es configurar nuestra IDE
// Primero de Android manager descargar el api de google map version 2
//luego entramos a la pagina de google apis, y creamos un proyecto, necesitamos un key para poder utilizar 
//el api, entonces le damos nuestra hueya digital seguido de una coma y nuestro SHA1
//de ahi podemos utilizar el API

public class UsandoGoogleMaps extends android.support.v4.app.FragmentActivity {
	
	private GoogleMap mapa = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapa = ((SupportMapFragment) getSupportFragmentManager()
				   .findFragmentById(R.id.map)).getMap();
		
		mapa.setOnMapClickListener(new OnMapClickListener() {
			public void onMapClick(LatLng point) {
				Projection proj = mapa.getProjection();
				Point coord = proj.toScreenLocation(point);
				
				Toast.makeText(
						UsandoGoogleMaps.this, 
						"Click\n" + 
						"Lat: " + point.latitude + "\n" +
						"Lng: " + point.longitude + "\n" +
						"X: " + coord.x + " - Y: " + coord.y,
						Toast.LENGTH_SHORT).show();
			}
		});
		
		mapa.setOnMapLongClickListener(new OnMapLongClickListener() {
			public void onMapLongClick(LatLng point) {
				Projection proj = mapa.getProjection();
				Point coord = proj.toScreenLocation(point);
				
				Toast.makeText(
						UsandoGoogleMaps.this, 
						"Click Largo\n" + 
						"Lat: " + point.latitude + "\n" +
						"Lng: " + point.longitude + "\n" +
						"X: " + coord.x + " - Y: " + coord.y,
						Toast.LENGTH_SHORT).show();
			}
		});
		
		mapa.setOnCameraChangeListener(new OnCameraChangeListener() {
			public void onCameraChange(CameraPosition position) {
				Toast.makeText(
						UsandoGoogleMaps.this, 
						"Cambio C�mara\n" + 
						"Lat: " + position.target.latitude + "\n" +
						"Lng: " + position.target.longitude + "\n" +
						"Zoom: " + position.zoom + "\n" +
						"Orientaci�n: " + position.bearing + "\n" +
						"�ngulo: " + position.tilt,
						Toast.LENGTH_SHORT).show();
			}
		});
		
		mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(
						UsandoGoogleMaps.this, 
						"Marcador pulsado:\n" + 
						marker.getTitle(),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{	
		switch(item.getItemId())
		{
			case R.id.menu_marcadores:
				mostrarMarcador(40.5, -3.5);
				break;
			case R.id.menu_lineas:
				mostrarLineas();
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private void mostrarMarcador(double lat, double lng)
	{
		mapa.addMarker(new MarkerOptions()
        .position(new LatLng(lat, lng))
        .title("Pais: Espa�a"));
	}
	
	private void mostrarLineas()
	{
		//Dibujo con Lineas
		
		PolylineOptions lineas = new PolylineOptions()
	        .add(new LatLng(45.0, -12.0))
	        .add(new LatLng(45.0, 5.0))
	        .add(new LatLng(34.5, 5.0))
	        .add(new LatLng(34.5, -12.0))
	        .add(new LatLng(45.0, -12.0));

		lineas.width(8);
		lineas.color(Color.RED);

		mapa.addPolyline(lineas);
		
		//Dibujo con pol�gonos, si queremos utilizar  esta funcion comentamos la anterior
		// y descomentamos esta, esta no mostrara un rectangulo en la seleccion
		
		//PolygonOptions rectangulo = new PolygonOptions()
		//              .add(new LatLng(45.0, -12.0),
		//            	   new LatLng(45.0, 5.0),
		//            	   new LatLng(34.5, 5.0),
		//            	   new LatLng(34.5, -12.0),
		//            	   new LatLng(45.0, -12.0));
		//
		//rectangulo.strokeWidth(8);
		//rectangulo.strokeColor(Color.RED);
		//
		//mapa.addPolygon(rectangulo);
	}
}

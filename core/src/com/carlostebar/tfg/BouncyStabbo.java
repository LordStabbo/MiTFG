package com.carlostebar.tfg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class BouncyStabbo extends ApplicationAdapter {
	//Instancio una variable que con la que manejar el estado de la ejecucion
	int estadoEjecucion = 0;
	//Instancio el SpriteBatch que contendra las animaciones del juego
	SpriteBatch miBatch;
	//Instancio la textura del fondo y un array de texturas para el personaje y su movimiento
	//Tambien instancio las texturas del fondo y de los obsrtaculos
	Texture obstaculoArriba;
	Texture obstaculoAbajo;
	Texture fondo;
	Texture[] personajePrincipal;
	//Hago variables con las que controlar las coordenadas del personaje y su velocidad
	double movimientoPersonaje = 0;
	double velocidadCaida=2;
	long personajeCoordY = 0;
	//Creo un Circle para las colisiones del personaje
	Circle circuloPersonaje;


	//Hago una variable para definir el espacio entre obstaculos y otro para el rango de espacio
	// en el que pueden "spawnear" los obstaculos, asi como un objeto de la clase Random con
	// el que se generaran los obstaculos de manera aleatoria
	float espaciadObstaculos = 48888800;
	float maxRangObstaculos;
	Random miGeneradorObstaculos;

	/*Hago variables para definir la valocidad y coordenadas de movimiento de los obstaculos*/
	float movimientoObstaculo = 4;

	//Hago una variable para controlar el numero de obstaculos que se van "a pasear" en bucle por
	// la pantalla, este se va a igualar con la velocidad de los obstaculos para que no se descuadre
	// por la pantalla
	int numObstaculos = (int) movimientoObstaculo;
	float [] obstaculoCoordX = new float[numObstaculos];
	float [] rangObstaculos = new float[numObstaculos];

	//Creo dos arrays de Rectangles para procesar las colisiones de los obstaculos superior e inferior
	Rectangle [] rectanguloObstaculoSuperior;
	Rectangle [] rectanguloObstaculoInferior;

	//hago una marca de control de la dfase del personaje principal
	int controlPersonje = 0;

	//Creo un renderer para trabajar con las colisiones entre objetos
	ShapeRenderer myRenderer;

	//Hago un metodo que recorra en bucle el estado del personaje para usarlo en el metodo render
	public void recorreEstados(){
		if(controlPersonje == 0){
			controlPersonje = 1;
		}else if(controlPersonje == 1){
			controlPersonje = 2;
		}else if (controlPersonje == 2){
			controlPersonje = 3;
		}else if (controlPersonje == 3){
			controlPersonje = 4;
		}else {
			controlPersonje = 0;
		}
	}

	public void plantaSuelo(){
		movimientoPersonaje = movimientoPersonaje + velocidadCaida;
		personajeCoordY -= movimientoPersonaje;
	}

	//metodo on create que se arranca al iniciar
	@Override
	public void create () {
		//Creo el SpriteBatch de texturas
		miBatch = new SpriteBatch();
		//Instancio la textura del fondo
		fondo = new Texture("fondo1.png");
		//Instancio la textura del personaje

		personajePrincipal= new Texture[5];
			personajePrincipal[0] = new Texture("ninja_salto1.png");
			personajePrincipal[1] = new Texture("ninja_salto2.png");
			personajePrincipal[2] = new Texture("ninja_salto3.png");
			personajePrincipal[3] = new Texture("ninja_salto4.png");
			personajePrincipal[4] = new Texture("ninja_salto5.png");

		personajeCoordY =  Gdx.graphics.getHeight()/2- personajePrincipal[controlPersonje].getHeight()/2;

		//Instancio las texturas de los obstaculos y declaro el rango en el que pueden aparecer
		obstaculoArriba = new Texture("obstaculo2.png");
		obstaculoAbajo = new Texture("obstaculo1.png");
		miGeneradorObstaculos = new Random();
		espaciadObstaculos = Gdx.graphics.getWidth()*3/4;

		//Creo el circle del personaje principal e instancio el array de rectangles de los obstaculos
		// para crear a estos mismos a medida que se vayan generando los obstaculos
		circuloPersonaje = new Circle();
		rectanguloObstaculoSuperior = new Rectangle[numObstaculos];
		rectanguloObstaculoInferior = new Rectangle[numObstaculos];

		for (int i = 0; i < numObstaculos; i++){

			rangObstaculos[i] = (miGeneradorObstaculos.nextFloat()- 0.5f) * (Gdx.graphics.getHeight() - espaciadObstaculos -200 );
			obstaculoCoordX[i] = Gdx.graphics.getWidth()/2 - obstaculoArriba.getWidth()/2+Gdx.graphics.getWidth()+i*espaciadObstaculos;

			rectanguloObstaculoSuperior[i] = new Rectangle();
			rectanguloObstaculoInferior[i] = new Rectangle();
		}

		myRenderer = new ShapeRenderer();

	}

	//renderizo las texturas cargadas en el metodo create
	@Override
	public void render () {

		miBatch.begin();

		//Con esto simulo la gravedad, de manera que la coordenada Y del personaje decrece
		// exponencialmente, dando la sensación de que está cayendo

		if(estadoEjecucion != 0) {



			recorreEstados();
			//Pinto el fondo
			miBatch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());



			//Con este bucle registro si el usuario toca la pantalla
			if(Gdx.input.justTouched()){
				//Esto hace que cada vez que el usuario toca, el personaje se mueve 50 uds hacia
				// arriba
				movimientoPersonaje = -50;
			}

			for(int i = 0; i< numObstaculos; i++){
				if(obstaculoCoordX[i] < - obstaculoArriba.getWidth()){
					obstaculoCoordX[i] += numObstaculos * espaciadObstaculos;
					rangObstaculos[i] = (miGeneradorObstaculos.nextFloat()- 0.5f) * (Gdx.graphics.getHeight() - espaciadObstaculos -200 );
				}else{
					obstaculoCoordX[i] -= movimientoObstaculo;
				}

				//Pinto los obstaculos
				miBatch.draw(obstaculoArriba, obstaculoCoordX[i], Gdx.graphics.getHeight() / 2+ espaciadObstaculos/2 + rangObstaculos[i]);
				miBatch.draw(obstaculoAbajo, obstaculoCoordX[i], Gdx.graphics.getHeight() / 2- espaciadObstaculos/2 - obstaculoAbajo.getHeight()+rangObstaculos[i]);

				//Defino sus rectangles
				rectanguloObstaculoSuperior[i] = new Rectangle(obstaculoCoordX[i],Gdx.graphics.getHeight() / 2+ espaciadObstaculos/2 + rangObstaculos[i], obstaculoArriba.getWidth(), obstaculoArriba.getHeight());
				rectanguloObstaculoInferior[i] = new Rectangle(obstaculoCoordX[i],Gdx.graphics.getHeight() / 2- espaciadObstaculos/2 - obstaculoAbajo.getHeight()+rangObstaculos[i], obstaculoAbajo.getWidth(), obstaculoAbajo.getHeight());

			}

			//Con este bucle hago que si el personaje llega a la parte de abajo de la pantalla su
			// movimiento hacia abajo  se detiene de manera que parece que el suelo ha parado la
			// caida del personaje
			if(personajeCoordY > 0 || movimientoPersonaje < 0){
				plantaSuelo();

			}
		}else{
			if(Gdx.input.justTouched()){
				estadoEjecucion = 1;
			}
		}

		//Pinto el personaje en todas sus fases
		miBatch.draw(personajePrincipal[controlPersonje], Gdx.graphics.getWidth() / 2, personajeCoordY);
		miBatch.end();

		//Inicio el shapeRenderer pasandole por parametro que rellene las texturas con las que trabaja
		myRenderer.begin(ShapeRenderer.ShapeType.Filled);
		myRenderer.setColor(Color.RED);

		circuloPersonaje.set(Gdx.graphics.getWidth()/2+personajePrincipal[controlPersonje].getWidth()/2, personajeCoordY+ personajePrincipal[controlPersonje].getHeight()/2, personajePrincipal[controlPersonje].getWidth()/2);

		//--------------Toda esta sección se ha utilizado para testear las colisiones entre objetos
		// 			pero lo conservo en el codigo por intereses academicos

		/*
		myRenderer.circle(circuloPersonaje.x, circuloPersonaje.y, circuloPersonaje.radius);

		for(int i = 0; i< numObstaculos; i++){
			myRenderer.rect(obstaculoCoordX[i],Gdx.graphics.getHeight() / 2+ espaciadObstaculos/2 + rangObstaculos[i], obstaculoArriba.getWidth(), obstaculoArriba.getHeight());
			myRenderer.rect(obstaculoCoordX[i],Gdx.graphics.getHeight() / 2- espaciadObstaculos/2 - obstaculoAbajo.getHeight()+rangObstaculos[i], obstaculoAbajo.getWidth(), obstaculoAbajo.getHeight());
		}

		*/


		myRenderer.end();





	}

}

package com.carlostebar.tfg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;



public class BouncyStabbo extends ApplicationAdapter {
	//Instancio una variable que con la que manejar el estado de la ejecucion
	int estadoEjecucion = 0;
	int desplazamiento =0;
	//Instancio el SpriteBatch que contendra las animaciones del juego
	SpriteBatch miBatch;
	//Instancio la textura del fondo y un array de texturas para el personaje y su movimiento
	//Tambien instancio las texturas del fondo y de los obsrtaculos
	Texture obstaculoArriba;
	Texture obstaculoAbajo;
	Texture fondo, fondoMuerte;

	Texture yesButton, noButton;
	Texture[] personajePrincipal;
	//Hago variables con las que controlar las coordenadas del personaje y su velocidad
	double movimientoPersonaje = 0;
	double velocidadCaida=0.9;
	long personajeCoordY = 0;
	//Creo un Circle para las colisiones del personaje
	Circle circuloPersonaje;
	float deltaTime = 0;


	//Hago una variable para definir el espacio entre obstaculos y otro para el rango de espacio
	// en el que pueden "spawnear" los obstaculos, asi como un objeto de la clase Random con
	// el que se generaran los obstaculos de manera aleatoria
	float espaciadObstaculos = 35;
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

	Rectangle formaYesButton, formaNoButton;

	//hago una marca de control de la dfase del personaje principal
	int controlPersonje = 0;

	//Creo un renderer para trabajar con las colisiones entre objetos
	ShapeRenderer myRenderer;


	//Creo una variable en la que almacenar los puntos del jugador y un BitMapFont para pintarlos
	// en pantalla
	int puntuacion = 0;
	int puntuacionImprimir;
	BitmapFont miFuente, miFuenteHint, miFuenteDeathCam;

	//Instancio la musica de la partida y los sonidos que voy a usar
	Music musicaFondo;
	Sound sonidoSalto;

	//Con esta variable hago que el testing de colisiones este activado o no en partida
	boolean testing = false;

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

	public void ponMusica(){
		musicaFondo = Gdx.audio.newMusic(Gdx.files.internal("saveThat.ogg"));
		musicaFondo.setLooping(true);
		musicaFondo.play();
	}

	public void plantaSuelo(){
		movimientoPersonaje = movimientoPersonaje + velocidadCaida;
		personajeCoordY -= movimientoPersonaje;
	}

	public void instanciaPartida(){
		personajeCoordY =  Gdx.graphics.getHeight()/2- personajePrincipal[controlPersonje].getHeight()/2;
		for (int i = 0; i < numObstaculos; i++){

			rangObstaculos[i] = (miGeneradorObstaculos.nextFloat()- 0.5f) * (Gdx.graphics.getHeight() - espaciadObstaculos -200 );
			obstaculoCoordX[i] = Gdx.graphics.getWidth()/2 - obstaculoArriba.getWidth()/2+Gdx.graphics.getWidth()+i*espaciadObstaculos;

			rectanguloObstaculoSuperior[i] = new Rectangle();
			rectanguloObstaculoInferior[i] = new Rectangle();
		}
	}

	//metodo on create que se arranca al iniciar
	@Override
	public void create () {

		/*Lo primero, al instanciar el juego, desactivo el renderizado constante para, posteriormente,
		* fijar a mano los FPS en el metodo render y que, asi, los Delta Times se apliquen correctamen
		* te, funcionando el juego igual en todos los dispositivos*/

		Gdx.graphics.setContinuousRendering(false);
		Gdx.graphics.requestRendering();

		//Creo el SpriteBatch de texturas
		miBatch = new SpriteBatch();
		//Instancio la textura de los fondos
		fondo = new Texture("fondo1.png");
		fondoMuerte = new Texture("fondoDeathCam.png");
		//Instancio la textura del personaje

		personajePrincipal= new Texture[5];
		personajePrincipal[0] = new Texture("ninja_salto1.png");
		personajePrincipal[1] = new Texture("ninja_salto2.png");
		personajePrincipal[2] = new Texture("ninja_salto3.png");
		personajePrincipal[3] = new Texture("ninja_salto4.png");
		personajePrincipal[4] = new Texture("ninja_salto5.png");



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


		//Instancio mi fuente
		miFuente = new BitmapFont();
		miFuente.setColor(Color.valueOf("05DBF2"));
		miFuente.getData().setScale(12);

		sonidoSalto = Gdx.audio.newSound(Gdx.files.internal("Jump.wav"));

		instanciaPartida();

		myRenderer = new ShapeRenderer();

		ponMusica();

	}

	//renderizo las texturas cargadas en el metodo create
	@Override
	public void render () {
		//Instancio los DeltaTimes para los objetos en movimiento
		deltaTime = Gdx.graphics.getDeltaTime();
		movimientoPersonaje += velocidadCaida * deltaTime;
		circuloPersonaje.y += velocidadCaida * deltaTime;

		miBatch.begin();

		//Con esto simulo la gravedad, de manera que la coordenada Y del personaje decrece
		// exponencialmente, dando la sensación de que está cayendo


		puntuacionImprimir = puntuacion/100;
		if(estadoEjecucion == 1) {

			desplazamiento++;

			recorreEstados();

			miBatch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


			//Con este bucle registro si el usuario toca la pantalla

			if(Gdx.input.justTouched()){
				//Esto hace que cada vez que el usuario toca, el personaje se mueve 50 uds hacia
				// arriba
				movimientoPersonaje = -25;
				sonidoSalto.play();
			}

			for(int i = 0; i< numObstaculos; i++){



				if(obstaculoCoordX[i] < - obstaculoArriba.getWidth()){
					obstaculoCoordX[i] += numObstaculos * espaciadObstaculos;
					rangObstaculos[i] = (miGeneradorObstaculos.nextFloat()- 0.5f) * (Gdx.graphics.getHeight() - espaciadObstaculos -200 );
				}else{
					obstaculoCoordX[i] -= movimientoObstaculo;

					//Si los obstaculos pasan de la posicion X del personaje, se suman 100 puntos,
					// si no pasan de ahi es porque el juego se ha detenido antes por una colision,
					// por lo que el jugador ya habría perdido para ese momento

					if(obstaculoCoordX[i] <= Gdx.graphics.getWidth()/2 ){
						puntuacion+= 5;

					}


				}

				//Pinto los obstaculos
				miBatch.draw(obstaculoArriba, obstaculoCoordX[i], Gdx.graphics.getHeight() / 2+ espaciadObstaculos/3 + rangObstaculos[i]);
				miBatch.draw(obstaculoAbajo, obstaculoCoordX[i], Gdx.graphics.getHeight() / 2- espaciadObstaculos/3 - obstaculoAbajo.getHeight()+rangObstaculos[i]);

				//Defino sus rectangles
				rectanguloObstaculoSuperior[i] = new Rectangle(obstaculoCoordX[i],Gdx.graphics.getHeight() / 2+ espaciadObstaculos/3 + rangObstaculos[i], obstaculoArriba.getWidth(), obstaculoArriba.getHeight());
				rectanguloObstaculoInferior[i] = new Rectangle(obstaculoCoordX[i],Gdx.graphics.getHeight() / 2- espaciadObstaculos/3 - obstaculoAbajo.getHeight()+rangObstaculos[i], obstaculoAbajo.getWidth(), obstaculoAbajo.getHeight());

			}

			//Con este bucle hago que si el personaje llega a la parte de abajo de la pantalla su
			// movimiento hacia abajo  se detiene de manera que parece que el suelo ha parado la
			// caida del personaje
			if(personajeCoordY > 0 || movimientoPersonaje < 0){
				personajeCoordY += movimientoPersonaje * deltaTime;
					plantaSuelo();
			}

			//Pinto las Puntuaciones
			miFuente.draw(miBatch, "Pts\n "+String.valueOf(puntuacionImprimir), Gdx.graphics.getWidth()/2-miFuente.getLineHeight()/2, Gdx.graphics.getHeight()/4-miFuente.getLineHeight()/2);

		}else if(estadoEjecucion == 0){
			miFuenteHint = new BitmapFont();
			miFuenteHint.setColor(Color.valueOf("05DBF2"));
			miFuenteHint.getData().setScale(7);
			miBatch.draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			miFuenteHint.draw(miBatch, "Touch the screen\n      to JUMP", Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/8);
			if(Gdx.input.justTouched()){
				estadoEjecucion = 1;
			}
		}else if(estadoEjecucion == 2){
			dameMenuMuerte();

			if(formaYesButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())){
					estadoEjecucion = 0;
					puntuacion = 0;
					movimientoPersonaje = 0;
					instanciaPartida();
				}

			if(formaNoButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())){
				System.exit(0);
			}

		}

		personajeCoordY += movimientoPersonaje * deltaTime;

		//Pinto el personaje en todas sus fases
		miBatch.draw(personajePrincipal[controlPersonje], Gdx.graphics.getWidth() / 2, personajeCoordY);


		//Inicio el shapeRenderer pasandole por parametro que rellene las texturas con las que trabaja


		circuloPersonaje.set(Gdx.graphics.getWidth() / 2 + personajePrincipal[controlPersonje].getWidth() / 2, personajeCoordY + personajePrincipal[controlPersonje].getHeight() / 2, personajePrincipal[controlPersonje].getWidth() / 2);

		if(testing == true) {
			myRenderer.begin(ShapeRenderer.ShapeType.Filled);
			myRenderer.setColor(new Color(1f, 0f, 1f, 0.5f));

		//--------------Toda esta sección se ha utilizado para testear las colisiones entre objetos
		// 			pero lo conservo en el codigo por intereses academicos


		myRenderer.circle(circuloPersonaje.x, circuloPersonaje.y, circuloPersonaje.radius);
		}

		for(int i = 0; i< numObstaculos; i++){
			if(testing == true) {
				myRenderer.rect(obstaculoCoordX[i], Gdx.graphics.getHeight() / 2 + espaciadObstaculos / 3 + rangObstaculos[i], obstaculoArriba.getWidth(), obstaculoArriba.getHeight());
				myRenderer.rect(obstaculoCoordX[i], Gdx.graphics.getHeight() / 2 - espaciadObstaculos / 3 - obstaculoAbajo.getHeight() + rangObstaculos[i], obstaculoAbajo.getWidth(), obstaculoAbajo.getHeight());
			}


			if(Intersector.overlaps(circuloPersonaje, rectanguloObstaculoInferior[i] )){
				Gdx.app.log("Colision", "Has chocado por abajo");
				estadoEjecucion = 2;
			}else if(Intersector.overlaps( circuloPersonaje, rectanguloObstaculoSuperior[i])){
				Gdx.app.log("Colision", "Has chocado por arriba");
				estadoEjecucion = 2;
			}
		}
		if(testing == true) {
			myRenderer.end();
		}

		miBatch.end();


		/*"Durmiendo" el proceso, fijo los FPS a 60 manualmente*/
		try {
			Thread.sleep(1000 / 60); // Sleep for approximately 16.67 milliseconds
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Gdx.graphics.requestRendering();
	}

	private void dameMenuMuerte() {
		miFuenteDeathCam = new BitmapFont();
		miFuenteDeathCam.setColor(Color.valueOf("#FF0000"));
		miFuenteDeathCam.getData().setScale(10);

		miBatch.draw(fondoMuerte, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		miFuenteDeathCam.draw(miBatch, "GAME OVER\n   "+puntuacionImprimir+" points\n   RETRY?", Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight() - miFuenteDeathCam.getLineHeight());

		yesButton = new Texture("yesButton.png");
		noButton = new Texture("noButton.png");

		int anchoYesButton = yesButton.getWidth();
		int altoYesButton = yesButton.getHeight();

		int anchoNoButton = noButton.getWidth();
		int altoNoButton = noButton.getHeight();

		miBatch.draw(yesButton, Gdx.graphics.getWidth() / 2 - yesButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - yesButton.getHeight() / 2 - yesButton.getHeight());
		miBatch.draw(noButton, Gdx.graphics.getWidth() / 2 - noButton.getWidth() / 2, (Gdx.graphics.getHeight() / 2 - noButton.getHeight() / 3) - yesButton.getHeight() * 2);

		formaYesButton = new Rectangle(Gdx.graphics.getWidth() / 2 - anchoYesButton / 2, Gdx.graphics.getHeight() / 2 - altoYesButton / 3 - altoYesButton, anchoYesButton, altoYesButton);
		formaNoButton = new Rectangle(Gdx.graphics.getWidth() / 2 - anchoNoButton / 2, (Gdx.graphics.getHeight() / 2 - altoNoButton / 3) - noButton.getHeight() * 2, anchoNoButton, altoNoButton);

	}

}

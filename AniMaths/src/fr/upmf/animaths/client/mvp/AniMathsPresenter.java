package fr.upmf.animaths.client.mvp;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.google.inject.Inject;

import fr.upmf.animaths.client.LoadEquationService;
import fr.upmf.animaths.client.LoadEquationServiceAsync;
import fr.upmf.animaths.client.LoadPathNamesService;
import fr.upmf.animaths.client.LoadPathNamesServiceAsync;
import fr.upmf.animaths.client.events.ExerciseSolvedEvent;
import fr.upmf.animaths.client.events.ExerciseSolvedHandler;
import fr.upmf.animaths.client.events.NewLineEvent;
import fr.upmf.animaths.client.events.NewLineHandler;
import fr.upmf.animaths.client.interaction.AniMathsCoreInteraction;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOIdentifier;
import fr.upmf.animaths.client.mvp.MathObject.MONumber;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

/**
 * The business logic for AniMaths.
 * 
 * @author Maxime Lefrançois
 * 
 */
public class AniMathsPresenter extends
		WidgetPresenter<AniMathsPresenter.Display> {

	public static EventBus eventBus;
	private static final LoadPathNamesServiceAsync aniMathsLoadPathNamesService = GWT
			.create(LoadPathNamesService.class);
	private static final LoadEquationServiceAsync aniMathsLoadEquationService = GWT
			.create(LoadEquationService.class);

	private MODynamicPresenter mODynamicPresenter;
	public List<MOBasicPresenter> mOBasicPresenters;
	// public Map<Integer,StaticManipulationWordingPresenter>
	// staticManipulationWordingPresenters = new
	// HashMap<String,StaticManipulationWordingPresenter>();
	private String tutoDirName = "tutorials";
	private String exoDirName = "exercises";
	private List<String> tutoPaths;
	private List<String> exoPaths;
	private boolean currentTuto;
	private String currentPath;

	public interface Display extends WidgetDisplay, HasMouseMoveHandlers {
		public MOWordingWidget getExerciseWordingWidget();

		public Button getTutorielButton();

		public Button getExerciseButton();

		public Button getPreviousButton();

		public Button getRestartButton();

		public Button getNextButton();
	}

	public static final Place PLACE = new Place("a");

	/**
	 * Returning a place will allow this presenter to automatically trigger when
	 * '#exercice' is passed into the browser URL.
	 */
	@Override
	public Place getPlace() {
		return PLACE;
	}

	@Inject
	public AniMathsPresenter(final Display display, final EventBus eventBus) {
		super(display, eventBus);
		AniMathsPresenter.eventBus = eventBus;
		bind();
	}

	@Override
	protected void onBind() {

		mODynamicPresenter = new MODynamicPresenter();
		mOBasicPresenters = new ArrayList<MOBasicPresenter>();

		loadPaths(tutoDirName);
		loadPaths(exoDirName);

		display.getExerciseButton().setEnabled(false);
		display.getTutorielButton().setEnabled(true);
		display.getNextButton().setEnabled(false);
		display.getPreviousButton().setEnabled(false);
		display.getRestartButton().setEnabled(false);

		display.getTutorielButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				display.getTutorielButton().setEnabled(false);
				new AniMathsMessageBox()
						.setAsStart(
								"<h3>Bienvenue dans l'application <strong>AniMaths</strong> !</h3>"
										+ "<div class='tuto'>Cette application a été réalisée par <br/>" +
												"<em>Maxime Lefrançois et Edouard Lopez</em>, <br/>"
										+ "dans le cadre du projet de génie lociciel du <br/>" +
												"<a href='http://imss.upmf-grenoble.fr/HMICOAP_43/0/fiche___formation/'>M2P Ingénierie de la Communication Personne Systèmes</a>"
										+ "<div class='txt-right'>À l'<em>Université Pierre Mendès France de Grenoble</em>.</div><hr/>"
										+ "<p>Pour profiter au mieux des possibilités d'interaction avec les objets mathématiques, assurez vous de bien "
										+ "utiliser le navigateur <strong>Mozilla Firefox</strong>, version 3 ou supérieure. Si vous ne l'avez pas, vous pouvez télécharger :</p>"
										+ "<ul><li><a href='http://www.mozilla.com/fr/' target='_blank'>la dernière version de Mozilla Firefox</a> sur le site officiel;</li>"
										+ "<li><a href='http://portableapps.com/apps/internet/firefox_portable' target='_blank'>la version portable de Mozilla Firefox</a>, si vous ne pouvez pas installer d'application sur l'ordinateur que vous utilisez.</li></ul></div>",
								"Suivant →", new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										new AniMathsMessageBox()
												.setAsStart(
														"<h3>Pour commencer&hellip;</h3>"
																+ "<p>Nous vous proposons de suivre un tutoriel pour <strong>vous familiariser avec cette application</strong>, "
																+ "grâce à des <em>exemples simples, des explications et des illustrations</em>.</p>"
																+ "<p>Notre but étant de <strong>faciliter l'interaction</strong> avec les différents objets mathématiques, "
																+ "AniMaths utilise une interface basée sur le paradigme de la <a href='http://fr.wikipedia.org/wiki/WIMP_%28informatique%29' target='_blank'>manipulation directe</a>.</p>",
														"Commencer →", new ClickHandler() {
															@Override
															public void onClick(ClickEvent event) {
																currentTuto = true;
																currentPath = tutoPaths.get(0);
																display.getPreviousButton().setEnabled(false);
																display.getNextButton().setEnabled(true);
																loadProblem(currentPath);
																display.getExerciseButton().setEnabled(true);
																display.getTutorielButton().setEnabled(true);
																display.getNextButton().setEnabled(true);
																display.getPreviousButton().setEnabled(false);
																display.getRestartButton().setEnabled(true);
															}
														});
									}
								});
			}
		});
		display.getExerciseButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				currentTuto = false;
				currentPath = exoPaths.get(0);
				display.getPreviousButton().setEnabled(false);
				display.getNextButton().setEnabled(true);
				loadProblem(currentPath);
			}
		});
		display.getPreviousButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				currentPath = getPreviousPath(currentPath);
				loadProblem(currentPath);
			}
		});
		display.getRestartButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				loadProblem(currentPath);
			}
		});
		display.getNextButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				currentPath = getNextPath(currentPath);
				loadProblem(currentPath);
			}
		});
		eventBus.addHandler(NewLineEvent.getType(), new NewLineHandler() {
			public void onNewLine(NewLineEvent event) {
				MOBasicPresenter newLine = new MOBasicPresenter(mODynamicPresenter
						.getElement().clone());
				mOBasicPresenters.add(newLine);
				RootPanel.get("view").insert(newLine.getDisplay().asWidget(),
						RootPanel.get("view").getWidgetCount() - 1);
				if (mODynamicPresenter.getElement() instanceof MOEquation) {
					MOEquation equation = ((MOEquation) mODynamicPresenter.getElement());
					if (equation.getLeftHandSide() instanceof MOIdentifier
							&& (equation.getRightHandSide() instanceof MONumber || equation
									.getRightHandSide() instanceof MOSignedElement))
						eventBus.fireEvent(new ExerciseSolvedEvent());
					if (equation.getRightHandSide() instanceof MOIdentifier
							&& (equation.getLeftHandSide() instanceof MONumber || equation
									.getLeftHandSide() instanceof MOSignedElement))
						eventBus.fireEvent(new ExerciseSolvedEvent());
				}
			}
		});

		eventBus.addHandler(ExerciseSolvedEvent.getType(),
				new ExerciseSolvedHandler() {
					public void onExerciseSolved(ExerciseSolvedEvent event) {
						System.out.println("Exercise Solved !");
						final AniMathsMessageBox congratulationBox = new AniMathsMessageBox();
						if (display.getNextButton().isEnabled()) {
							congratulationBox.setAsCorrect(new MOWordingWidget(
									"Vous avez fini cet exercice ! Bravo !"));
							congratulationBox.addButton("↻ Recommencer", new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									congratulationBox.hide();
									display.getRestartButton().click();
								}
							}, false);
							congratulationBox.addButton("Passer au suivant →",
									new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											congratulationBox.hide();
											display.getNextButton().click();
										}
									}, true);
						} else if (currentTuto) {
							congratulationBox.setAsCorrect(new MOWordingWidget(
									"Vous avez fini le tutoriel ! Bravo !"));
							congratulationBox.addButton("↻ Recommencer le tutoriel",
									new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											congratulationBox.hide();
											display.getTutorielButton().click();
										}
									}, false);
							congratulationBox.addButton("↻ Recommencer cette étape",
									new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											congratulationBox.hide();
											display.getRestartButton().click();
										}
									}, false);
							congratulationBox.addButton("Passer aux exercices",
									new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											congratulationBox.hide();
											display.getNextButton().click();
										}
									}, true);
						} else {
							congratulationBox.setAsCorrect(new MOWordingWidget(
									"Vous avez fini le tutoriel ! Bravo !"));
							congratulationBox.addButton("↻ Recommencer le tutoriel",
									new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											congratulationBox.hide();
											display.getTutorielButton().click();
										}
									}, false);
							congratulationBox.addButton("↻ Recommencer cette exercice",
									new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											congratulationBox.hide();
											display.getRestartButton().click();
										}
									}, false);
							congratulationBox.addButton("↻ Recommencer les exercices",
									new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											congratulationBox.hide();
											display.getExerciseButton().click();
										}
									}, true);
						}
					}
				});
	}

	@Override
	protected void onPlaceRequest(final PlaceRequest request) {
		// Grab the 'id' from the request and load the 'id' equation.
		final String id = request.getParameter("id", null);
		if (id != null) {
			loadProblem(id);
		}
	}

	@Override
	protected void onUnbind() {
		// Add unbind functionality here for more complex presenters.
	}

	@Override
	public void refreshDisplay() {
		// This is called when the presenter should pull the latest data
		// from the server, etc. In this case, there is nothing to do.
	}

	@Override
	public void revealDisplay() {
		// Nothing to do. This is more useful in UI which may be buried
		// in a tab bar, tree, etc.
	}

	/**
	 * @return
	 */
	private void loadProblem(final String path) {

		final AniMathsMessageBox loadingBox = new AniMathsMessageBox();
		loadingBox.setAsLoading(new MOWordingWidget(
				"Chargement de l'exercice, veuillez patientez quelques instants."));
		final Timer timer = new Timer() {
			@Override
			public void run() {
				loadingBox
						.setAsError(new MOWordingWidget(
								"<h3>Erreur lors du chargement du problème</h3>"
										+ path
										+ "<p>Le document XML décrivant le problème est probablement mal formé. "
										+ "Veuillez vérifier la validité du document et recommencer.</p>"));
			}
		};
		timer.schedule(10000);

		for (MOBasicPresenter basicPresenter : mOBasicPresenters)
			basicPresenter.unbind();
		mOBasicPresenters.clear();
		while (RootPanel.get("view").getElement().getChildCount() > 1)
			RootPanel.get("view").getElement().getFirstChild().removeFromParent();
		// Set up the callback object.
		final AsyncCallback<String> callback = new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				loadingBox
						.setAsError(new MOWordingWidget(
								"<h3>Erreur lors de la récupération de l'exercice</h3> "
										+ "<p>Un problème de communication entre le client et le serveur est survenue, "
										+ "pour résoudre le problème vous pouvez tenter les actions suivantes :"
										+ "<ul><li>veuillez tester votre connexion ;</li>"
										+ "<li>vérifier que vous ne travaillez pas en mode hors connexion ;</li>"
										+ "<li>ré-essayez ultérieurement.</li></ul>"
										+ "<p>Si aucune de ces actions n'a résolu le problème, <a href='mailto:admin@animaths.org'>contacter l'administrateur</a>."));
			}

			public void onSuccess(String result) {
				// System.out.println(result);
				Element root = XMLParser.parse(result).getDocumentElement();
				int level = root.hasAttribute("level") ? Integer.parseInt(root
						.getAttribute("level")) : 1000;
				NodeList children = root.getChildNodes();
				// display.getExerciseWordingWidget().setWording("Isoler ", new
				// MOIdentifier("x")," dans l'équation ", eq);
				int k = 0;
				for (int i = 0; i < children.getLength(); i++) {
					Node n = children.item(i);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						k++;
						assert k <= 2;
						if (k == 1)
							display.getExerciseWordingWidget().parse((Element) n);
						else
							mODynamicPresenter.init(MOElement.parse((Element) n));
					}
				}

				AniMathsCoreInteraction.setPresenterAndRun(mODynamicPresenter, level);

				RootPanel.get("currentPath").clear();
				RootPanel.get("currentPath").add(new InlineLabel(path));
				loadingBox.hide();
				timer.cancel();
			}

		};

		aniMathsLoadEquationService.loadEquation(path, callback);
	}

	/**
	 * @return
	 */
	private void loadPaths(final String path) {
		final AniMathsMessageBox loadingBox = new AniMathsMessageBox();
		loadingBox.setAsLoading(new MOWordingWidget(
				"Chargement, veuillez patientez quelques instants."));

		// Set up the callback object.
		final AsyncCallback<List<String>> callback = new AsyncCallback<List<String>>() {
			public void onFailure(Throwable caught) {
				loadingBox
						.setAsError(new MOWordingWidget(
								"<h3>Erreur lors de la récupération de l'exercice</h3> "
										+ "<p>Un problème de communication entre le client et le serveur est survenue, "
										+ "pour résoudre le problème vous pouvez tenter les actions suivantes :"
										+ "<ul><li>veuillez tester votre connexion ;</li>"
										+ "<li>vérifier que vous ne travaillez pas en mode hors connexion ;</li>"
										+ "<li>ré-essayez ultérieurement.</li></ul>"
										+ "<p>Si aucune de ces actions n'a résolu le problème, <a href='mailto:admin@animaths.org'>contacter l'administrateur</a>."));
			}

			public void onSuccess(List<String> result) {
				setPathNames(path, result);
				if (path.equals(tutoDirName))
					display.getTutorielButton().click();
			}
		};
		aniMathsLoadPathNamesService.loadPathNames(path, callback);
	}

	public void setPathNames(String path, List<String> pathNames) {
		System.out.println("setPathNames : " + path);
		for (String name : pathNames)
			System.out.println(">>>" + name);
		if (path.equals(tutoDirName))
			tutoPaths = pathNames;
		else if (path.equals(exoDirName))
			exoPaths = pathNames;
	}

	public String getPreviousPath(String path) {
		System.out.println("getPreviousPath: "+path);
		int index = tutoPaths.indexOf(path);
		System.out.println("index: "+index);
		List<String> list = tutoPaths;
		if (index == -1) {
			index = exoPaths.indexOf(path);
			assert index != -1;
			list = exoPaths;
		}
		if (index == 0)
			display.getPreviousButton().setEnabled(false);
		display.getNextButton().setEnabled(true);
		return list.get(Math.max(0, index - 1));
	}

	public String getNextPath(String path) {
		System.out.println("getNextPath: "+path);
		int index = tutoPaths.indexOf(path);
		List<String> list = tutoPaths;
		if (index == -1) {
			index = exoPaths.indexOf(path);
			assert index != -1;
			list = exoPaths;
		}
		if (index == list.size() - 1)
			display.getNextButton().setEnabled(false);
		display.getPreviousButton().setEnabled(true);
		return list.get(Math.min(list.size() - 1, index + 1));
	}

}
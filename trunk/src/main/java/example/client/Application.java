package example.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import example.client.color.controller.ColorFormController;
import example.client.color.controller.ColorDialogBoxController;
import example.client.color.logic.ColorUpdateLogic;
import example.client.color.logic.ColorUpdateLogicImpl;
import example.client.core.model.DefaultModelFactory;
import example.client.core.model.ModelFactory;
import example.client.core.view.WidgetFactory;
import example.client.core.view.DefaultWidgetFactory;
import example.client.color.model.ColorFormModel;
import example.client.color.model.ColorDialogModel;
import example.client.color.view.ColorForm;
import example.client.color.view.ColorDialogBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    // The prerequisit factories
    final ModelFactory modelFactory = new DefaultModelFactory();
    final WidgetFactory widgetFactory = new DefaultWidgetFactory(modelFactory);

    // Main form with the model
    final ColorFormModel formModel = new ColorFormModel(modelFactory);
    final ColorForm colorForm = new ColorForm(widgetFactory, formModel);

    // Dialog box with the model
    final ColorDialogModel dialogModel = new ColorDialogModel(modelFactory);
    final ColorDialogBox dialog = new ColorDialogBox(widgetFactory, dialogModel);

    // Wire everything together with controllers. Please note that in this particular context,
    // the form model is the master model.
    final ColorUpdateLogic logic = new ColorUpdateLogicImpl(formModel);
    new ColorFormController(formModel, new ColorDialogBoxController(dialog, dialogModel, logic));

    RootPanel.get().add(colorForm);

//  final Label label = new Label();
//  final Button rpcButton = new Button("Run GWT Async RPC!");
//  rpcButton.setTitle("This will execute the RPC call to the Java server");
//  rpcButton.addClickListener(new ClickListener() {
//     public void onClick(Widget sender) {
//
//        // (1) Create the client proxy. Note that although you are
//        // creating the
//        // service interface proper, you cast the result to the
//        // asynchronous
//        // version of
//        // the interface. The cast is always safe because the generated
//        // proxy
//        // implements the asynchronous interface automatically.
//        SampleRemoteServiceAsync sampleRemoteService = (SampleRemoteServiceAsync) GWT
//              .create(SampleRemoteService.class);
//
//        // (2) Specify the URL at which our service implementation is
//        // running.
//        // Note that the target URL must reside on the same domain and
//        // port from
//        // which the host page was served.
//        ServiceDefTarget endpoint = (ServiceDefTarget) sampleRemoteService;
//
//        String moduleRelativeURL = GWT.getModuleBaseURL()
//              + "sampleRemoteService";
//        endpoint.setServiceEntryPoint(moduleRelativeURL);
//
//        // (3) Create an asynchronous callback to handle the result.
//        AsyncCallback callback = new AsyncCallback() {
//           public void onSuccess(Object result) {
//              // do some UI stuff to show success
//              label.setText((String) result);
//           }
//
//           public void onFailure(Throwable caught) {
//              // do some UI stuff to show failure
//              label.setText("DAMMIT! This didnt work.");
//           }
//        };
//
//        // (4) Make the call. Control flow will continue immediately and
//        // later
//        // 'callback' will be invoked when the RPC completes.
//        sampleRemoteService.doComplimentMe(callback);
//
//     }
//   });
//
//  RootPanel.get().add(new HTML("<br /><br />"));
//  RootPanel.get().add(label);
//  RootPanel.get().add(rpcButton);
//
//  RootPanel.get().add(new HTML("<br /><br />"));
//  RootPanel.get().add(new Label("change URL to append \"?locale=fr\" to see the fancy french text"));
//
//  RootPanel.get().add(new HTML("<br /><br />"));
//  AppConstants appConstants = (AppConstants) GWT.create(AppConstants.class);
//  String constantTest = appConstants.constant1();
//  RootPanel.get().add(new Label ("i18n constantTest - " + constantTest));
//
//  RootPanel.get().add(new HTML("<br /><br />"));
//  AppMessages appMessages = (AppMessages) GWT.create(AppMessages.class);
//  String messageTest = appMessages.message1("zipededoodaaa");
//  RootPanel.get().add(new Label ("i18n messageTest - " + messageTest));

  }
}

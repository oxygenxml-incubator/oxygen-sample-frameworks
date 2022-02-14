/**
 * Action that adds a new student in the file.
 */
class AddStudentAction extends sync.actions.AbstractAction {

	constructor(editor) {
		super('M2 S')
		this.editor = editor;
	}

	getDisplayName() {
		return 'Add student';
  }

	actionPerformed(callback) {
		var name = window.prompt("Please enter the student name");
		var surname = window.prompt("Please enter the student surname");
		if (name && surname) {
			this.editor.getActionsManager().invokeOperation('InsertFragmentOperation', {
					fragment: `<student name="${name}" surname="${surname}"/>`
				}, callback);
		} else {
			callback && callback();
		}
	};
}


/**
 * Framework's main clas.
 */
class StudentsExtension extends sync.ext.Extension {

  constructor() {
    super();
  }
	
	editorCreated(editor) {

	  editor.getActionsManager()
			.registerAction('add.student', new AddStudentAction(editor));

		editor.listen(sync.api.Editor.EventTypes.ACTIONS_LOADED, e => {
			var actionsConfig = e.actionsConfiguration;
			var toolbars = actionsConfig.toolbars || [];
			var studetsToolbar = toolbars.filter(t => t.name === 'Students')[0];

	    if (studetsToolbar) {
				studetsToolbar.children.push({id: 'add.student', type: 'action'});
			}
		});
  }
}

sync.ext.Registry.extension = new StudentsExtension();

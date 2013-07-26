log = (message) ->
	console.log(message)


$ ->
	band = new Band el: $("#main")



class Band extends Backbone.View
	initialize: ->
		new BandHeader el: $(@el).find("#bandHeader")

		$("#myWishes ul li").each (i, li) ->
			new Wish el: li


class BandHeader extends Backbone.View
	events:
		"click  #bandEdit"      : "edit"
		"click  #bandEditSave"  : "editSave"
		"click  #bandEditCancel": "editCancel"

	initialize: ->
		@name = $(@el).find("h1").html()

	edit: ->
		$(@el).find("h1").html(@createInput(@name))
		$("#bandEdit").hide()
		$("#bandEditConfirm").show()

	editSave: ->
		@log("editSave")

	editCancel: ->
		$(@el).find("h1").html(@name)
		$("#bandEdit").show()
		$("#bandEditConfirm").hide()

	createInput: (text) ->
		"<input type='text' value='"+text+"' />"




class Wish extends Backbone.View
	events:
		"click  .wishEdit"      : "edit"
		"click  .wishEditSave"  : "editSave"
		"click  .wishEditCancel": "editCancel"
		"click  .wishDelete"    : "delete"
		"click  .wishDeleteYes" : "deleteYes"
		"click  .wishDeleteNo"  : "deleteNo"

	initialize: ->
		@id = $(@el).data("wish")
		@text = $(@el).children(".wishDescription").html()

	edit: ->
		$(@el).children(".wishDescription").html(@createTextArea())
		$(@el).children(".wishButtonPanel").hide()
		$(@el).children(".wishEditConfirm").show()

	editSave: ->
		log("editSave "+@id)

	editCancel: ->
		$(@el).children(".wishDescription").html(@text)
		$(@el).children(".wishButtonPanel").show()
		$(@el).children(".wishEditConfirm").hide()

	delete: ->
  		$(@el).find(".wishDeleteConfirm").show()

  deleteYes: ->
  		log("deleteYes "+@id)

  deleteNo: ->
  		$(@el).find(".wishDeleteConfirm").hide()

	createTextArea: ->
		"<textarea name='description' rows='5' cols='70'>"+
				@text+
		"</textarea>"






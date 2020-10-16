const DOMStrings = {
    richtext: '.richtext',
    btnAdd: 'btn-add_step',
    btnSubmit: 'btn-submit_step',
    editor: 'editor',
    text: '.ql-editor',
    listSteps: 'list_steps',
    stepTitle: 'step_title',
    btnInstrName: 'change_instruction_name', 
    instrNameInput: 'instruction_name_input',
    instrChangeName: 'instruction_change_name',
    btnSubmitInstrName: 'btn-submit_instruction_name'
}

const result = {
    name: '',
    steps: []    
}

function submitStep() {
    let tit = document.getElementById(DOMStrings.stepTitle).value;
    tit = `<h3>${tit}</h3>`;
    const content = document.querySelector(DOMStrings.text).innerHTML;

    if(content && content !== '<p><br></p>' && tit!='<h3></h3>') {
        const step = {
            title: tit,
            body: content 
        }
    result.steps.push(step);

    document.querySelector(DOMStrings.text).innerHTML = '';
    document.getElementById(DOMStrings.stepTitle).value = '';
    
    const node = document.createElement("li");
    node.innerHTML = `<span>${result.steps[result.steps.length - 1].title}: ${result.steps[result.steps.length - 1].body}</span>`;
    document.getElementById(DOMStrings.listSteps).appendChild(node);
 
    document.querySelector(DOMStrings.richtext).style.display = 'none';
    document.getElementById(DOMStrings.btnAdd).style.display = 'block';
    }
}

function addStep() {
    document.querySelector(DOMStrings.richtext).style.display = 'block';
    document.getElementById(DOMStrings.btnAdd).style.display = 'none';
}

document.getElementById(DOMStrings.btnInstrName).addEventListener('click', () => {
    const title = document.getElementsByTagName('h1')[0];
    const input = document.getElementById(DOMStrings.instrNameInput);
    input.value = title.textContent;    

    title.style.display = 'none';
    input.style.display = 'inline-block';
    document.getElementById(DOMStrings.btnSubmitInstrName).style.display = 'inline-block';
    document.getElementById(DOMStrings.btnInstrName).style.display = 'none';
})

document.getElementById(DOMStrings.btnSubmitInstrName).addEventListener('click', () => {
    const input = document.getElementById(DOMStrings.instrNameInput);
    const title = document.getElementsByTagName('h1')[0];
    title.textContent = input.value;

    title.style.display = 'inline-block';
    input.style.display = 'none';
    document.getElementById(DOMStrings.btnSubmitInstrName).style.display = 'none';
    document.getElementById(DOMStrings.btnInstrName).style.display = 'inline-block';
})

async function saveInstruction() {

    if(result.name === '') {
        result.name = document.getElementsByTagName('h1')[0].textContent;
    }
    const instruction = JSON.stringify(result);
    console.log(instruction);

    try {
        const response = await fetch(`http://localhost:8084/create-instruction?name=${result.name}`, {
            method: 'POST', 
            body: instruction
        });
    } catch (e) {
        console.log(e);
    }
}

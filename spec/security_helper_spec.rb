require_relative 'setup'

describe 'SecurityHelper' do

  it 'registers signatures with SecurityHelper' do
    # clear the fast engines
    engines.clear
    # setup the fast engines - this creates a warning
    load( "${this}/../lib/fast-rsa-engine.rb" )

    expect( engines.size ).to eq( 12 )
  end
  
end
